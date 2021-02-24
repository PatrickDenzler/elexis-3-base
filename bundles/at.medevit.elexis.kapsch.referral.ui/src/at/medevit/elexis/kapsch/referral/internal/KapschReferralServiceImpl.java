package at.medevit.elexis.kapsch.referral.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;

import at.medevit.elexis.kapsch.referral.KapschReferralService;
import ch.elexis.core.constants.XidConstants;
import ch.elexis.core.data.events.ElexisEventDispatcher;
import ch.elexis.core.model.ch.BillingLaw;
import ch.elexis.core.services.holder.ConfigServiceHolder;
import ch.elexis.core.types.Gender;
import ch.elexis.data.Fall;
import ch.elexis.data.Kontakt;
import ch.elexis.data.Patient;

@Component(service = KapschReferralService.class)
public class KapschReferralServiceImpl implements KapschReferralService {
	
	public Optional<String> getPatientReferralUrl(Patient patient){
		URL url = getUrl();
		Map<String, Object> params = getPatientParameterMap(patient);
		if (params != null) {
			String getParams = getMapAsGetParams(params);
			if (getParams != null) {
				LoggerFactory.getLogger(getClass())
					.info("Referral URL " + url.toString() + "?" + getParams);
				return Optional.of(url.toString() + "?" + getParams);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<String> sendPatient(Patient patient){
        URL url = getUrl();
		Map<String, Object> params = getPatientParameterMap(patient);
		if (params != null) {
			byte[] postDataBytes = getMapAsPostData(params);
			if (postDataBytes != null) {
				try {
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
					conn.setDoOutput(true);
					conn.getOutputStream().write(postDataBytes);
					
					BufferedReader in =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					
					while ((inputLine = in.readLine()) != null)
						System.out.println(inputLine);
					in.close();
				} catch (IOException e) {
					LoggerFactory.getLogger(getClass()).error("Error sending post data", e);
				}
			}
		}
		return Optional.empty();
	}
	
	private byte[] getMapAsPostData(Map<String, Object> params){
		try {
			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			return postData.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.getLogger(getClass()).error("Error getting post data", e);
			return null;
		}
	}

	private String getMapAsGetParams(Map<String, Object> params){
		try {
			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			return postData.toString();
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.getLogger(getClass()).error("Error getting get params", e);
			return null;
		}
	}
	
	private Map<String, Object> getPatientParameterMap(Patient patient){
		Map<String, Object> params = new LinkedHashMap<>();
		// patient data
		params.put("PID", patient.getPatCode());
		params.put("Lastname", patient.getName());
		params.put("Firstname", patient.getVorname());
		params.put("Street", patient.getAnschrift().getStrasse());
		params.put("PostalCode", patient.getAnschrift().getPlz());
		params.put("City", patient.getAnschrift().getOrt());
		params.put("Birthday", patient.getGeburtsdatum());
		params.put("Sex", getSex(patient));
		params.put("SSN", getSsn(patient));
		params.put("eMail", patient.get(Patient.FLD_E_MAIL));
		params.put("Phone", patient.get(Patient.FLD_PHONE1));
		params.put("Mobile", patient.get(Patient.FLD_MOBILEPHONE));
		
		Fall fall = (Fall) ElexisEventDispatcher.getSelected(Fall.class);
		if (fall != null) {
			Kontakt garant = fall.getGarant();
			if (garant != null) {
				params.put("GuarantorName", garant.get(Kontakt.FLD_NAME1));
				params.put("GuarantorStreet", garant.getAnschrift().getStrasse());
				params.put("GuarantorPostalCode", garant.getAnschrift().getPlz());
				params.put("GuarantorCity", garant.getAnschrift().getOrt());
				params.put("GuarantorPhone", garant.get(Kontakt.FLD_PHONE1));
				params.put("GuarantorGln", getGln(garant));
				if (StringUtils.isNotEmpty((String) fall.getExtInfoStoredObjectByKey("VEKANr"))) {
					params.put("InsuranceCardNr", (String) fall.getExtInfoStoredObjectByKey("VEKANr"));
				}
				if (StringUtils
					.isNotEmpty((String) fall.getExtInfoStoredObjectByKey("VEKAValid"))) {
					params.put("InsuranceCardExpiryDate",
						(String) fall.getExtInfoStoredObjectByKey("VEKAValid"));
				}
				params.put("EntryReason", fall.getConfiguredBillingSystemLaw().name());
				if(fall.getConfiguredBillingSystemLaw() == BillingLaw.UVG) {
					if(StringUtils.isNotEmpty(fall.getRequiredString("Unfallnummer"))) {
						params.put("AccidentNr", fall.getRequiredString("Unfallnummer"));
					}
					if (StringUtils.isNotEmpty(fall.getInfoString("Unfalldatum"))) {
						params.put("DateOfAccident", fall.getRequiredString("Unfalldatum"));
					}
				}
			}
		}
		
		return params;
	}
	
	private String getGln(Kontakt contact){
		return contact.getXid(XidConstants.DOMAIN_EAN);
	}
	
	private String getSsn(Patient patient){
		return patient.getXid(XidConstants.DOMAIN_AHV);
	}
	
	private String getSex(Patient patient){
		Gender gender = patient.getGender();
		if (gender == Gender.FEMALE) {
			return "W";
		} else if (gender == Gender.MALE) {
			return "M";
		}
		return "U";
	}
	
	private URL getUrl(){
		try {
			String configEndpoint = ConfigServiceHolder.getMandator(
				KapschReferralService.CONFIG_ENDPOINT, KapschReferralService.ENDPOINT_TEST);
			if (KapschReferralService.ENDPOINT_PRODUCTIV.equals(configEndpoint)) {
				return new URL("https://referral.kapsch.health/webapp");
			}
			return new URL("https://referral-test.kapsch.health/webapp");
		} catch (MalformedURLException e) {
			LoggerFactory.getLogger(getClass()).error("Error getting url", e);
			return null;
		}
	}
}