 
package ch.elexis.covid.cert.ui.handler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import ch.elexis.core.common.ElexisEventTopics;
import ch.elexis.core.data.util.NoPoUtil;
import ch.elexis.core.model.IBillable;
import ch.elexis.core.model.IBilled;
import ch.elexis.core.model.ICodeElementBlock;
import ch.elexis.core.model.ICoverage;
import ch.elexis.core.model.IDocumentLetter;
import ch.elexis.core.model.IEncounter;
import ch.elexis.core.model.IPatient;
import ch.elexis.core.model.builder.IEncounterBuilder;
import ch.elexis.core.services.IContextService;
import ch.elexis.core.services.ILocalDocumentService;
import ch.elexis.core.services.holder.BillingServiceHolder;
import ch.elexis.core.services.holder.ContextServiceHolder;
import ch.elexis.core.services.holder.CoreModelServiceHolder;
import ch.elexis.core.ui.text.TextContainer;
import ch.elexis.data.Brief;
import ch.elexis.data.Konsultation;

public class CovidAttestSz {
	
	@Inject
	private ILocalDocumentService localDocumentService;
	
	@Inject
	private IContextService contextService;
	
	private TextContainer textContainer;
	
	@Execute
	public void execute(){
		if (textContainer == null) {
			textContainer = new TextContainer();
		}
		Optional<IPatient> activePatient = contextService.getActivePatient();
		activePatient.ifPresent(patient -> {
			Map<String, ICodeElementBlock> blocks = CovidHandlerUtil.getConfiguredBlocks();
			if (!blocks.isEmpty()) {
				Optional<ICoverage> szCoverage =
					CovidHandlerUtil.getCoverageWithLaw(patient, CovidHandlerUtil.SZ_LAWS);
				List<IDocumentLetter> existingLetters = CovidHandlerUtil.getLettersAt(patient,
					LocalDate.now(), new String[] {
						CovidHandlerUtil.ATTEST_POSITIV_LETTER_NAME,
						CovidHandlerUtil.ATTEST_NEGATIV_LETTER_NAME
				});
				if (existingLetters.isEmpty()) {
					if (szCoverage.isEmpty()) {
						szCoverage = CovidHandlerUtil.createSzCoverage(patient);
					}
					Optional<IEncounter> antigenEncounter;
					if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
						"Test Resultat", "Wurde der Patient positiv getestet?")) {
						if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
							"PCR Test", "Wurde ein PCR Test gemacht?")) {
							antigenEncounter = billAntigen(szCoverage.get());
							if (antigenEncounter.isPresent()) {
								Optional<IEncounter> pcrEncounter = billPcr(szCoverage.get());
								pcrEncounter.ifPresent(encounter -> {
									if (isBilled(antigenEncounter.get(), "01.99.1100")) {
										removeBilled(encounter, "01.99.1100");
									}
								});
							}
						} else {
							antigenEncounter = billAntigen(szCoverage.get());
						}
						Konsultation letterKons =
							(Konsultation) NoPoUtil.loadAsPersistentObject(antigenEncounter.get());
						Brief letterPositiv = textContainer.createFromTemplateName(letterKons,
							CovidHandlerUtil.ATTEST_POSITIV_LETTER_NAME, Brief.UNKNOWN, null, null);
						CovidHandlerUtil.openLetter(
							NoPoUtil.loadAsIdentifiable(letterPositiv, IDocumentLetter.class).get(),
							localDocumentService);
					} else {
						antigenEncounter = billAntigen(szCoverage.get());
						Konsultation letterKons =
							(Konsultation) NoPoUtil.loadAsPersistentObject(antigenEncounter.get());
						Brief letterNegativ = textContainer.createFromTemplateName(letterKons,
							CovidHandlerUtil.ATTEST_NEGATIV_LETTER_NAME, Brief.UNKNOWN, null, null);
						CovidHandlerUtil.openLetter(
							NoPoUtil.loadAsIdentifiable(letterNegativ, IDocumentLetter.class).get(),
							localDocumentService);
					}
					ContextServiceHolder.get().postEvent(ElexisEventTopics.EVENT_UPDATE, patient);
				} else {
					if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
						"Vorhandene Test Bescheinigung",
						"Es wurde heute bereits ein Test Bescheinigung ausgestellt.\nMöchten Sie diese anzeigen?")) {
						CovidHandlerUtil.openLetter(existingLetters.get(0), localDocumentService);
					}
				}
			}
		});
	}
	
	private boolean isBilled(IEncounter encounter, String code){
		return encounter.getBilled().stream().filter(b -> code.equals(b.getCode())).findFirst()
			.isPresent();
	}
	
	private void removeBilled(IEncounter encounter, String code){
		for (IBilled billed : new ArrayList<>(encounter.getBilled())) {
			if (code.equals(billed.getCode())) {
				BillingServiceHolder.get().removeBilled(billed, encounter);
			}
		}
	}
	
	private Optional<IEncounter> billAntigen(ICoverage coverage){
		ICodeElementBlock szBlock =
			CovidHandlerUtil.getConfiguredBlocks().get(CovidHandlerUtil.CFG_SZ_BLOCKID);
		if (szBlock != null) {
			IEncounter encounter = new IEncounterBuilder(CoreModelServiceHolder.get(), coverage,
				contextService.getActiveMandator().get()).buildAndSave();
			// bill the block
			szBlock.getElements(encounter).stream().filter(el -> el instanceof IBillable)
				.map(el -> (IBillable) el)
				.forEach(billable -> BillingServiceHolder.get().bill(billable, encounter, 1));
			contextService.getRootContext().setTyped(encounter);
			return Optional.of(encounter);
		} else {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Fehler",
				"Kein Selbstzahler PCR Block konfiguriert.");
		}
		return Optional.empty();
	}
	
	private Optional<IEncounter> billPcr(ICoverage coverage){
		ICodeElementBlock szBlock =
			CovidHandlerUtil.getConfiguredBlocks().get(CovidHandlerUtil.CFG_SZ_PCR_BLOCKID);
		if (szBlock != null) {
			IEncounter encounter = new IEncounterBuilder(CoreModelServiceHolder.get(), coverage,
				contextService.getActiveMandator().get()).buildAndSave();
			// bill the block
			szBlock.getElements(encounter).stream().filter(el -> el instanceof IBillable)
				.map(el -> (IBillable) el)
				.forEach(billable -> BillingServiceHolder.get().bill(billable, encounter, 1));
			contextService.getRootContext().setTyped(encounter);
			return Optional.of(encounter);
		} else {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Fehler",
				"Kein Selbstzahler PCR Block konfiguriert.");
		}
		return Optional.empty();
	}
}