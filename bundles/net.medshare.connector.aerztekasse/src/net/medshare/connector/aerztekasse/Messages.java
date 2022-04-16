/**********************************************************************
 * Copyright (c) 2005 IBM Corporation and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM - Initial API and implementation
 **********************************************************************/
package net.medshare.connector.aerztekasse;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.medshare.connector.aerztekasse.messages";
    public static String InvoiceOutputter_ChangeDir = ch.elexis.core.l10n.Messages.InvoiceOutputter_ChangeDir;
    public static String InvoiceOutputter_DoExport = ch.elexis.core.l10n.Messages.InvoiceOutputter_DoExport;
    public static String InvoiceOutputter_Error = ch.elexis.core.l10n.Messages.InvoiceOutputter_Error;
    public static String InvoiceOutputter_ErrorCreateZip = ch.elexis.core.l10n.Messages.InvoiceOutputter_ErrorCreateZip;
    public static String InvoiceOutputter_ErrorHttpPost = ch.elexis.core.l10n.Messages.InvoiceOutputter_ErrorHttpPost;
    public static String InvoiceOutputter_ErrorInInvoice = ch.elexis.core.l10n.Messages.InvoiceOutputter_ErrorInInvoice;
    public static String InvoiceOutputter_ErrorInvoice = ch.elexis.core.l10n.Messages.InvoiceOutputter_ErrorInvoice;
    public static String InvoiceOutputter_FailureInvoiceNr = ch.elexis.core.l10n.Messages.InvoiceOutputter_FailureInvoiceNr;
    public static String InvoiceOutputter_InvoiceOutputDir = ch.elexis.core.l10n.Messages.InvoiceOutputter_InvoiceOutputDir;
    public static String InvoiceOutputter_NewState = ch.elexis.core.l10n.Messages.InvoiceOutputter_NewState;
    public static String InvoiceOutputter_SuccessInvoiceNr = ch.elexis.core.l10n.Messages.InvoiceOutputter_SuccessInvoiceNr;
    public static String InvoiceOutputter_TransmisionAK = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmisionAK;
    public static String InvoiceOutputter_TransmisionAKFailure = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmisionAKFailure;
    public static String InvoiceOutputter_TransmisionAKSuccess = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmisionAKSuccess;
    public static String InvoiceOutputter_TransmissionFailed = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmissionFailed;
    public static String InvoiceOutputter_TransmittedInvoices = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmittedInvoices;
    public static String InvoiceOutputter_TransmittedInvoicesTitle = ch.elexis.core.l10n.Messages.InvoiceOutputter_TransmittedInvoicesTitle;
    public static String Preferences_GlobalSettings = ch.elexis.core.l10n.Messages.Preferences_GlobalSettings;
    public static String Preferences_LocalSettingsFor = ch.elexis.core.l10n.Messages.Preferences_LocalSettingsFor;
    public static String Preferences_MandantSettingsFor = ch.elexis.core.l10n.Messages.Preferences_MandantSettingsFor;
    public static String Preferences_Password = ch.elexis.core.l10n.Messages.Preferences_Password;
    public static String Preferences_URL = ch.elexis.core.l10n.Messages.Preferences_URL;
    public static String Preferences_UseGlobalSettings = ch.elexis.core.l10n.Messages.Preferences_UseGlobalSettings;
    public static String Preferences_Username = ch.elexis.core.l10n.Messages.Preferences_Username;
    public static String Preferences_undefiniert = ch.elexis.core.l10n.Messages.Preferences_undefiniert;
    public static String XMLExporter_Change = ch.elexis.core.l10n.Messages.XMLExporter_Change;
    public static String XMLExporter_ErrorInBill = ch.elexis.core.l10n.Messages.XMLExporter_ErrorInBill;
    public static String XMLExporter_PleaseEnterOutputDirectoryForBills = ch.elexis.core.l10n.Messages.XMLExporter_PleaseEnterOutputDirectoryForBills;

    static { // load message values from bundle file
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
}
