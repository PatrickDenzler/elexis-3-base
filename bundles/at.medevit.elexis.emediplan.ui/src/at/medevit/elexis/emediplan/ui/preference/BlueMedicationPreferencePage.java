package at.medevit.elexis.emediplan.ui.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import at.medevit.elexis.emediplan.core.BlueMedicationConstants;
import ch.elexis.core.data.activator.CoreHub;
import ch.elexis.core.data.service.ConfigServiceHolder;
import ch.elexis.core.ui.preferences.SettingsPreferenceStore;

public class BlueMedicationPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	public BlueMedicationPreferencePage(){
		super(GRID);
		setPreferenceStore(new SettingsPreferenceStore(CoreHub.globalCfg));
		setDescription("Blue Medication Einstellungen");
	}
	
	@Override
	protected void createFieldEditors(){
		addField(new StringFieldEditor(BlueMedicationConstants.CFG_HIN_PROXY_HOST, "HIN Proxy Host",
			getFieldEditorParent()));
		addField(new StringFieldEditor(BlueMedicationConstants.CFG_HIN_PROXY_PORT, "HIN Proxy Port",
			getFieldEditorParent()));
		
		addField(new BooleanFieldEditor(BlueMedicationConstants.CFG_URL_STAGING,
			"Test Server verwenden", getFieldEditorParent()));
	}
	
	@Override
	public void init(IWorkbench workbench){
		String initialvalue =
			ConfigServiceHolder.get().get(BlueMedicationConstants.CFG_HIN_PROXY_HOST, null);
		if (initialvalue == null) {
			ConfigServiceHolder.get().set(BlueMedicationConstants.CFG_HIN_PROXY_HOST,
				BlueMedicationConstants.DEFAULT_HIN_PROXY_HOST);
		}
		
		initialvalue =
			ConfigServiceHolder.get().get(BlueMedicationConstants.CFG_HIN_PROXY_PORT, null);
		if (initialvalue == null) {
			ConfigServiceHolder.get().set(BlueMedicationConstants.CFG_HIN_PROXY_PORT,
				BlueMedicationConstants.DEFAULT_HIN_PROXY_PORT);
		}
	}
}