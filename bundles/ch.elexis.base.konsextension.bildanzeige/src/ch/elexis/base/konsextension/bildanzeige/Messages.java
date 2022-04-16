package ch.elexis.base.konsextension.bildanzeige;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ch.elexis.base.konsextension.bildanzeige.messages"; //$NON-NLS-1$
	public static String Bild_BadVersionNUmber = ch.elexis.core.l10n.Messages.Bild_BadVersionNUmber;
	public static String Bild_NoPatientSelected = ch.elexis.core.l10n.Messages.Bild_NoPatientSelected;
	public static String Bild_VersionConflict = ch.elexis.core.l10n.Messages.Bild_VersionConflict;
	public static String Bild_YouShouldSelectAPatient = ch.elexis.core.l10n.Messages.Bild_YouShouldSelectAPatient;
	public static String BildanzeigeFenster_Cannot = ch.elexis.core.l10n.Messages.BildanzeigeFenster_Cannot;
	public static String BildanzeigeFenster_Close = ch.elexis.core.l10n.Messages.BildanzeigeFenster_Close;
	public static String BildanzeigeFenster_Create = ch.elexis.core.l10n.Messages.BildanzeigeFenster_Create;
	public static String BildanzeigeFenster_Error = ch.elexis.core.l10n.Messages.BildanzeigeFenster_Error;
	public static String BildanzeigeFenster_ErrorWriting = ch.elexis.core.l10n.Messages.BildanzeigeFenster_ErrorWriting;
	public static String BildanzeigeFenster_Export = ch.elexis.core.l10n.Messages.BildanzeigeFenster_Export;
	public static String BildImportDialog_DescriptionOfImage = ch.elexis.core.l10n.Messages.BildImportDialog_DescriptionOfImage;
	public static String BildImportDialog_ImportCaption = ch.elexis.core.l10n.Messages.BildImportDialog_ImportCaption;
	public static String BildImportDialog_ImportMessage = ch.elexis.core.l10n.Messages.BildImportDialog_ImportMessage;
	public static String BildImportDialog_ImportTitle = ch.elexis.core.l10n.Messages.BildImportDialog_ImportTitle;
	public static String BildImportDialog_JPEG_Description = ch.elexis.core.l10n.Messages.BildImportDialog_JPEG_Description;
	public static String BildImportDialog_PNG_Description = ch.elexis.core.l10n.Messages.BildImportDialog_PNG_Description;
	public static String BildImportDialog_StorageFormat = ch.elexis.core.l10n.Messages.BildImportDialog_StorageFormat;
	public static String BildImportDialog_TitleOfImage = ch.elexis.core.l10n.Messages.BildImportDialog_TitleOfImage;
	public static String KonsExtension_ErrorLoading = ch.elexis.core.l10n.Messages.KonsExtension_ErrorLoading;
	public static String KonsExtension_Image = ch.elexis.core.l10n.Messages.KonsExtension_Image;
	public static String KonsExtension_ImageCouldnotBeLoaded = ch.elexis.core.l10n.Messages.KonsExtension_ImageCouldnotBeLoaded;
	public static String KonsExtension_InsertImage = ch.elexis.core.l10n.Messages.KonsExtension_InsertImage;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	private Messages(){}
}
