/*******************************************************************************
 * Copyright (c) 2006-2010, Gerry Weirich and Elexis
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Weirich - initial implementation
 *    
 *******************************************************************************/
package ch.elexis.agenda.data;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ch.elexis.agenda.data.messages"; //$NON-NLS-1$
	public static String Import_Agenda_cancelled = ch.elexis.core.l10n.Messages.Import_Agenda_cancelled;
	public static String Import_Agenda_creatingTables = ch.elexis.core.l10n.Messages.Import_Agenda_creatingTables;
	public static String Import_Agenda_errorsDuringImport = ch.elexis.core.l10n.Messages.Import_Agenda_errorsDuringImport;
	public static String Import_Agenda_importFromJavaAgenda = ch.elexis.core.l10n.Messages.Import_Agenda_importFromJavaAgenda;
	public static String Import_Agenda_importingAgenda = ch.elexis.core.l10n.Messages.Import_Agenda_importingAgenda;
	public static String Import_Agenda_importingApps = ch.elexis.core.l10n.Messages.Import_Agenda_importingApps;
	public static String Import_Agenda_importWasCancelled = ch.elexis.core.l10n.Messages.Import_Agenda_importWasCancelled;
	public static String TagesNachricht_29 = ch.elexis.core.l10n.Messages.TagesNachricht_29;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	private Messages(){}
}
