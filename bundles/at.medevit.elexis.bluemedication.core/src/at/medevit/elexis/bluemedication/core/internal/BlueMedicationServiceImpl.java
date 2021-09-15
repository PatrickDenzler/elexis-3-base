package at.medevit.elexis.bluemedication.core.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.LocalDate;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import at.medevit.elexis.bluemedication.core.BlueMedicationConstants;
import at.medevit.elexis.bluemedication.core.BlueMedicationService;
import at.medevit.elexis.bluemedication.core.UploadResult;
import at.medevit.elexis.emediplan.core.EMediplanService;
import ch.elexis.core.common.ElexisEventTopics;
import ch.elexis.core.data.activator.CoreHub;
import ch.elexis.core.data.events.ElexisEventDispatcher;
import ch.elexis.core.model.prescription.EntryType;
import ch.elexis.data.Artikel;
import ch.elexis.data.Brief;
import ch.elexis.data.Mandant;
import ch.elexis.data.Patient;
import ch.elexis.data.PersistentObject;
import ch.elexis.data.Prescription;
import ch.rgw.tools.Result;
import ch.rgw.tools.Result.SEVERITY;
import ch.rgw.tools.TimeTool;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.EMediplanGenerationApi;
import io.swagger.client.api.ExtractionAndConsolidationApi;
import io.swagger.client.api.MediCheckApi;

@Component(property = EventConstants.EVENT_TOPIC + "=" + ElexisEventTopics.BASE + "emediplan/ui/create")
public class BlueMedicationServiceImpl implements BlueMedicationService, EventHandler {
	
	private static Logger logger = LoggerFactory.getLogger(BlueMedicationServiceImpl.class);

	private boolean proxyActive;
	private String oldProxyHost;
	private String oldProxyPort;
	
	private Map<Object, UploadResult> pendingUploadResults;
	
	private ExecutorService executor;

	@Reference
	private EMediplanService eMediplanService;

	@Activate
	public void activate(){
		pendingUploadResults = new HashMap<>();
		executor = Executors.newCachedThreadPool();
	}
	
	/**
	 * Set the HIN proxy as system property. <b>Remember to call deInitProxy</b>
	 */
	private void initProxy(){
		if (!proxyActive) {
			// get proxy settings and store old values
			Properties systemSettings = System.getProperties();
			oldProxyHost = systemSettings.getProperty("http.proxyHost"); //$NON-NLS-1$
			oldProxyPort = systemSettings.getProperty("http.proxyPort"); //$NON-NLS-1$
			
			// set new values
			systemSettings.put("http.proxyHost", CoreHub.localCfg.get( //$NON-NLS-1$
				BlueMedicationConstants.CFG_HIN_PROXY_HOST,
				BlueMedicationConstants.DEFAULT_HIN_PROXY_HOST));
			systemSettings.put("http.proxyPort", CoreHub.localCfg.get( //$NON-NLS-1$
				BlueMedicationConstants.CFG_HIN_PROXY_PORT,
				BlueMedicationConstants.DEFAULT_HIN_PROXY_PORT));
			System.setProperties(systemSettings);
			proxyActive = true;
		}
	}
	
	/**
	 * Reset the proxy values in the system properties.
	 */
	private void deInitProxy(){
		if (proxyActive) {
			Properties systemSettings = System.getProperties();
			if (oldProxyHost != null) {
				systemSettings.put("http.proxyHost", oldProxyHost); //$NON-NLS-1$
			}
			if (oldProxyPort != null) {
				systemSettings.put("http.proxyPort", oldProxyPort); //$NON-NLS-1$
			}
			System.setProperties(systemSettings);
			proxyActive = false;
		}
	}
	
	@Override
	public Result<UploadResult> uploadDocument(Patient patient, File document, String resulttyp) {
		initProxy();
		workaroundGet();
		try {
			ExtractionAndConsolidationApi apiInstance = new ExtractionAndConsolidationApi();
			apiInstance.getApiClient().setBasePath(getAppBasePath());
			File externalData = document;
			String patientFirstName = patient.getVorname();
			String patientLastName = patient.getName();
			String patientSex = patient.getGender().name();
			LocalDate patientBirthdate = LocalDate.now();
			try {
				boolean uploadedMediplan = false;
				File internalData = null;
				if ("chmed".equals(resulttyp) && useRemoteImport() && hasPrescriptionsWithValidIdType(patient)) {
					Mandant mandant = ElexisEventDispatcher.getSelectedMandator();
					if (mandant != null) {
						try {
							ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
							eMediplanService.exportEMediplanPdf(mandant, patient,
									getPrescriptions(patient, "all"), true, pdfOutput);
							File pdfFile = File
								.createTempFile("eMediplan_" + System.currentTimeMillis(), ".pdf");
							try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
								fos.write(pdfOutput.toByteArray());
								fos.flush();
							}
							internalData = pdfFile;
							uploadedMediplan = true;
						} catch (IOException e) {
							logger.error("Error creating eMediplan",
								e);
							return new Result<UploadResult>(
								SEVERITY.ERROR, 0, e.getMessage(), null, false);
						}
					}
				}
				ApiResponse<?> response =
					apiInstance.dispatchPostWithHttpInfo(internalData, externalData,
						patientFirstName, patientLastName, patientSex, patientBirthdate,
						"", "", "", "", "");
				if (response.getStatusCode() >= 300) {
					return new Result<UploadResult>(SEVERITY.ERROR, 0,
						"Response status code was [" + response.getStatusCode() + "]", null, false);
				}
				if (response.getData() == null) {
					return new Result<UploadResult>(SEVERITY.ERROR,
						0, "Response has no data", null, false);
				}
				// successful upload
				@SuppressWarnings("unchecked")
				io.swagger.client.model.UploadResult data =
					((ApiResponse<io.swagger.client.model.UploadResult>) response).getData();
				return new Result<UploadResult>(new UploadResult(appendPath(getBasePath(),
						data.getUrl() + "&mode=embed"), data.getId(), resulttyp, uploadedMediplan));
			} catch (ApiException e) {
				if (e.getCode() == 400 || e.getCode() == 422) {
					// error result code should be evaluated
					try {
						Gson gson = new Gson();
						io.swagger.client.model.ErrorResult[] mcArray = gson.fromJson(e.getResponseBody(),
								io.swagger.client.model.ErrorResult[].class);
						if (mcArray != null && mcArray.length > 0) {
							return new Result<UploadResult>(SEVERITY.ERROR, 0,
									"Error result code [" + mcArray[0].getCode() + "]", null, false);
						}
					} catch (Exception je) {
						logger
							.warn("Could not parse code 400 exception content ["
								+ e.getResponseBody() + "]");
					}
				}
				logger.error("Error uploading Document", e);
				return new Result<UploadResult>(SEVERITY.ERROR, 0,
					e.getMessage(), null, false);
			}
		} finally {
			deInitProxy();
		}
	}
	
	private class CheckApiClient extends ApiClient {

		private String redirectUrl;

		public CheckApiClient() {
			super();
			// do not follow redirects
			getHttpClient().setFollowRedirects(false);
			getHttpClient().setFollowSslRedirects(false);
		}

		public String selectHeaderContentType(String[] contentTypes) {
			return "application/x-chmed16a";
		};

		@Override
		public RequestBody serialize(Object obj, String contentType) throws ApiException {
			if (obj instanceof String) {
				return RequestBody.create(MediaType.parse(contentType), (String) obj);
			} else {
				throw new ApiException("Content type \"" + contentType + "\" is not supported");
			}
		}

		@Override
		public <T> T handleResponse(Response response, Type returnType) throws ApiException {
			if (response.code() == 302) {
				redirectUrl = response.header("Location", null);
				return null;
			}
			throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), null);
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}
	}

	@Override
	public Result<UploadResult> uploadCheck(Patient patient) {
		initProxy();
		workaroundGet();
		try {
			CheckApiClient client = new CheckApiClient();

			MediCheckApi apiInstance = new MediCheckApi(client);
			apiInstance.getApiClient().setBasePath(getAppBasePath());

			Mandant mandant = ElexisEventDispatcher.getSelectedMandator();
			if (mandant != null) {
				try {
					ByteArrayOutputStream jsonOutput = new ByteArrayOutputStream();
					eMediplanService.exportEMediplanChmed(mandant, patient, getPrescriptions(patient, "all"), true,
							jsonOutput);
					apiInstance
							.checkPostWithHttpInfo(new String(jsonOutput.toByteArray(), "UTF-8"));
					if (client.getRedirectUrl() != null) {
						return new Result<UploadResult>(new UploadResult(client.getRedirectUrl(), "", "check", true));
					} else {
						return new Result<UploadResult>(SEVERITY.ERROR, 0, "No redirect", null, false);
					}
				} catch (IOException e) {
					logger.error("Error creating eMediplan", e);
					return new Result<UploadResult>(SEVERITY.ERROR, 0, e.getMessage(), null, false);
				}
			} else {
				return new Result<UploadResult>(SEVERITY.ERROR, 0, "No active mandator", null, false);
			}
		} catch (ApiException e) {
			if (e.getCode() == 400 || e.getCode() == 422) {
				// error result code should be evaluated
				try {
					Gson gson = new Gson();
					io.swagger.client.model.ErrorResult[] mcArray = gson.fromJson(e.getResponseBody(),
							io.swagger.client.model.ErrorResult[].class);
					if (mcArray != null && mcArray.length > 0) {
						return new Result<UploadResult>(SEVERITY.ERROR, 0,
								"Error result code [" + mcArray[0].getCode() + "]", null, false);
					}
				} catch (Exception je) {
					logger
							.warn("Could not parse code 400 exception content [" + e.getResponseBody() + "]");
				}
			}
			logger.error("Error uploading Document", e);
			return new Result<UploadResult>(SEVERITY.ERROR, 0, e.getMessage(), null, false);
		} finally {
			deInitProxy();
		}
	}

	@Override
	public Result<String> emediplanNotification(Patient patient) {
		initProxy();
		workaroundGet();
		try {
			EMediplanGenerationApi apiInstance = new EMediplanGenerationApi();
			apiInstance.getApiClient().setBasePath(getAppBasePath());
			
			LocalDate birthDate = null;
			if(StringUtils.isNotBlank(patient.getGeburtsdatum())) {
				TimeTool patBirthDay = new TimeTool(patient.getGeburtsdatum());
				birthDate = LocalDate.of(patBirthDay.toLocalDate().getYear(), patBirthDay.toLocalDate().getMonthValue(),
						patBirthDay.toLocalDate().getDayOfMonth());
			}

			ApiResponse<?> response = apiInstance.notificationEmediplanPostWithHttpInfo(patient.getVorname(),
					patient.getName(), patient.getGender().name(), birthDate);
			return new Result<String>(response.toString());
		} catch (ApiException e) {
			if (e.getCode() == 400 || e.getCode() == 422) {
				// error result code should be evaluated
				try {
					Gson gson = new Gson();
					io.swagger.client.model.ErrorResult[] mcArray = gson.fromJson(e.getResponseBody(),
							io.swagger.client.model.ErrorResult[].class);
					if (mcArray != null && mcArray.length > 0) {
						return new Result<String>(SEVERITY.ERROR, 0, "Error result code [" + mcArray[0].getCode() + "]",
								null, false);
					}
				} catch (Exception je) {
					logger
							.warn("Could not parse code 400 exception content [" + e.getResponseBody() + "]");
				}
			}
			logger.error("Error performing notification", e);
			return new Result<String>(SEVERITY.ERROR, 0, e.getMessage(), null, false);
		} finally {
			deInitProxy();
		}
	}

	/**
	 * Perform a workaround get until HIN fixed POST issue
	 * 
	 */
	private void workaroundGet(){
		try {
			ExtractionAndConsolidationApi apiInstance = new ExtractionAndConsolidationApi();
			apiInstance.getApiClient().setBasePath(getAppBasePath());
			
			logger.warn("Performing workaround GET request");
			apiInstance.downloadIdComparisonChmedGet("workaround", false);
		} catch (Exception e) {
			// ignore
		}
	}
	
	private String appendPath(String pathStart, String pathEnd){
		if (pathStart.endsWith("/") || pathEnd.startsWith("/")) {
			return pathStart + pathEnd;
		} else if (pathStart.endsWith("/") && pathEnd.startsWith("/")) {
			return pathStart + pathEnd.substring(1);
		} else {
			return pathStart + "/" + pathEnd;
		}
	}
	
	private String getBasePath(){
		if (CoreHub.globalCfg.get(BlueMedicationConstants.CFG_URL_STAGING, false)) {
			return "http://staging.bluemedication.hin.ch";
		} else {
			return "http://bluemedication.hin.ch";
		}
	}
	
	private String getAppBasePath(){
		return appendPath(getBasePath(), "/api/v1");
	}
	
	private Result<String> getError(String text) {
		return new Result<String>(SEVERITY.ERROR, 0, text, text, false);
	}

	private Result<String> getOk(String text) {
		return new Result<String>(SEVERITY.OK, 0, text, text, false);
	}

	@Override
	public Result<String> downloadEMediplan(UploadResult uploadResult){
		initProxy();
		try {
			ExtractionAndConsolidationApi apiInstance = new ExtractionAndConsolidationApi();
			apiInstance.getApiClient().setBasePath(getAppBasePath());
			
			if (uploadResult.isUploadedMediplan()) {
				ApiResponse<String> response =
					apiInstance.downloadIdComparisonChmedGetWithHttpInfo(uploadResult.getId(),
						true);
				if (response.getStatusCode() >= 300) {
					return getError("Response status code was [" + response.getStatusCode() + "]");
				}
				if (response.getData() == null) {
					return getError("Response has no data");
				}
				return getOk(response.getData());
			} else {
				ApiResponse<String> response =
					apiInstance.downloadIdExtractionChmedGetWithHttpInfo(uploadResult.getId(),
						true);
				if (response.getStatusCode() >= 300) {
					return getError("Response status code was [" + response.getStatusCode() + "]");
				}
				if (response.getData() == null) {
					return getError("Response has no data");
				}
				return getOk(response.getData());
			}
		} catch (ApiException e) {
			logger.error("Error downloading Document", e);
			return getError(e.getMessage());
		} finally {
			deInitProxy();
		}
	}
	
	@Override
	public Result<String> downloadPdf(UploadResult uploadResult) {
		initProxy();
		try {
			ExtractionAndConsolidationApi apiInstance = new ExtractionAndConsolidationApi();
			apiInstance.getApiClient().setBasePath(getAppBasePath());

			ApiResponse<File> response = apiInstance
					.downloadIdExtractionExtendedpdfGetWithHttpInfo(uploadResult.getId(), true);
			if (response.getStatusCode() >= 300) {
				return getError("Response status code was [" + response.getStatusCode() + "]");
			}
			if (response.getData() == null) {
				return getError("Response has no data");
			}
			return getOk(response.getData().getAbsolutePath());
		} catch (ApiException e) {
			logger.error("Error downloading Document Pdf", e);
			return getError(e.getMessage());
		} finally {
			deInitProxy();
		}
	}

	@Override
	public void addPendingUploadResult(Object object,
		UploadResult uploadResult){
		pendingUploadResults.put(object, uploadResult);
	}
	
	@Override
	public Optional<UploadResult> getPendingUploadResult(
		Object object){
		return Optional.ofNullable(pendingUploadResults.get(object));
	}
	
	@Override
	public void removePendingUploadResult(Object object){
		pendingUploadResults.remove(object);
	}
	
	private boolean useRemoteImport(){
		return CoreHub.globalCfg.get(BlueMedicationConstants.CFG_USE_IMPORT, false);
	}
	
	private boolean hasPrescriptionsWithValidIdType(Patient patient){
		List<Prescription> allPrescriptions = getPrescriptions(patient, "all");
		List<Prescription> nonValidIdPrescriptions = allPrescriptions.stream()
			.filter(p -> getIdType(p.getArtikel()) == 1).collect(Collectors.toList());
		return nonValidIdPrescriptions.isEmpty();
	}
	
	private List<Prescription> getPrescriptions(Patient patient, String medicationType){
		if ("all".equals(medicationType)) {
			List<Prescription> ret = new ArrayList<Prescription>();
			ret.addAll(patient.getMedication(EntryType.FIXED_MEDICATION));
			ret.addAll(patient.getMedication(EntryType.RESERVE_MEDICATION));
			ret.addAll(patient.getMedication(EntryType.SYMPTOMATIC_MEDICATION));
			return ret;
		} else if ("fix".equals(medicationType)) {
			return patient.getMedication(EntryType.FIXED_MEDICATION);
		} else if ("reserve".equals(medicationType)) {
			return patient.getMedication(EntryType.RESERVE_MEDICATION);
		} else if ("symptomatic".equals(medicationType)) {
			return patient.getMedication(EntryType.SYMPTOMATIC_MEDICATION);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Get the eMediplan id type for an Artikel. Must match method of
	 * <i>at.medevit.elexis.emediplan.core.model.chmed16a.Medicament</i>.
	 * 
	 * @param article
	 * @return
	 */
	private int getIdType(Artikel article){
		if (article != null) {
			String gtin = article.getEAN();
			if (gtin != null && !gtin.isEmpty() && gtin.startsWith("76")) {
				return 2;
			}
			String pharma = article.getPharmaCode();
			if (pharma == null || pharma.isEmpty()) {
				pharma = article.get(Artikel.FLD_SUB_ID);
			}
			if (pharma != null && !pharma.isEmpty()
				&& !pharma.startsWith(PersistentObject.MAPPING_ERROR_MARKER)) {
				return 3;
			}
		}
		return 1;
	}

	@Override
	public void startPollForResult(final Object object, final UploadResult uploadResult, Consumer<Object> onSuccess) {
		executor.execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// ignore
			}
			logger.info("Start polling for [" + uploadResult.getId() + "]");
			// configure HIN proxy for apache http client
			HttpHost proxy = new HttpHost(
				CoreHub.localCfg.get(BlueMedicationConstants.CFG_HIN_PROXY_HOST,
							BlueMedicationConstants.DEFAULT_HIN_PROXY_HOST),
				Integer.parseInt(CoreHub.localCfg.get(BlueMedicationConstants.CFG_HIN_PROXY_PORT,
							BlueMedicationConstants.DEFAULT_HIN_PROXY_PORT)),
					"http");
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			HttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();

			HttpGet httpget = new HttpGet(getAppBasePath() + "/status/" + uploadResult.getId());
			int maxRetry = 30; // default timeout 30 sec -> 15 min.
			int statusCode = 204;
			String content = null;
			try {
				while (statusCode == 204 && maxRetry > 0) {
					HttpResponse response = httpclient.execute(httpget);
					statusCode = response.getStatusLine().getStatusCode();
					if (response.getEntity() != null) {
						content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
					}
					maxRetry--;
				}
			} catch (IOException e) {
				logger.error("Error performing polling for [" + uploadResult.getId() + "]", e);
				return;
			}
			if (statusCode == 200) {
				if (StringUtils.isNotBlank(content) && content.contains("COMPLETED")) {
					logger.info("Finished [" + uploadResult.getId() + "] completed");
					onSuccess.accept(object);
				} else {
					logger.info("Finished [" + uploadResult.getId() + "] not completed");
					removePendingUploadResult(object);
				}
			} else {
				logger.warn("Got response code [" + statusCode + "] for [" + uploadResult.getId()
						+ "] clearing pending upload");
				removePendingUploadResult(object);
			}
		});
	}

	@Override
	public void handleEvent(Event event) {
		Object property = event.getProperty("org.eclipse.e4.data");
		if (property instanceof Brief) {
			Patient patient = (Patient) ((Brief) property).getPatient();
			if (patient != null) {
				emediplanNotification(patient);
			}
		}
	}
}
