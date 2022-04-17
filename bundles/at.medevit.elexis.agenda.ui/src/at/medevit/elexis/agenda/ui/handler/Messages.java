package at.medevit.elexis.agenda.ui.handler;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String DeleteHandler_0;
	public static String DeleteHandler_1;
	public static String DeleteHandler_2;
	public static String DeleteHandler_3;
	public static String DeleteHandler_4;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
