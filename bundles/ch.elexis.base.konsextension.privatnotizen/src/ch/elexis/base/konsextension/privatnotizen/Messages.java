package ch.elexis.base.konsextension.privatnotizen;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ch.elexis.base.konsextension.privatnotizen.messages"; //$NON-NLS-1$
	public static String KonsExtension_noteActionLabel = ch.elexis.core.l10n.Messages.KonsExtension_noteActionLabel;
	public static String KonsExtension_noteActionXREFText = ch.elexis.core.l10n.Messages.KonsExtension_noteActionXREFText;
	public static String NotizInputDialog_noteDlgMessage = ch.elexis.core.l10n.Messages.NotizInputDialog_noteDlgMessage;
	public static String NotizInputDialog_noteDlgText = ch.elexis.core.l10n.Messages.NotizInputDialog_noteDlgText;
	public static String NotizInputDialog_noteDlgTitle = ch.elexis.core.l10n.Messages.NotizInputDialog_noteDlgTitle;
	public static String NotizInputDialog_notYourNoteTitle = ch.elexis.core.l10n.Messages.NotizInputDialog_notYourNoteTitle;
	public static String NotizInputDialog_notYourNoteMessage = ch.elexis.core.l10n.Messages.NotizInputDialog_notYourNoteMessage;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	private Messages(){}
}
