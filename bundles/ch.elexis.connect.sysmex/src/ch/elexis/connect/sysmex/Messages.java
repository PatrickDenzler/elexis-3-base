package ch.elexis.connect.sysmex;


import org.eclipse.osgi.util.NLS;
public class Messages extends NLS {
  public static final String BUNDLE_NAME = "ch.elexis.connect.sysmex.messages";

  public static String SysmexAction_ConnectionName = ch.elexis.core.l10n.Messages.SysmexAction_ConnectionName;
  public static String SysmexAction_DefaultPort = ch.elexis.core.l10n.Messages.SysmexAction_DefaultPort;
  public static String SysmexAction_DefaultTimeout = ch.elexis.core.l10n.Messages.SysmexAction_DefaultTimeout;
  public static String SysmexAction_DefaultParams = ch.elexis.core.l10n.Messages.SysmexAction_DefaultParams;
  public static String SysmexAction_DeviceName = ch.elexis.core.l10n.Messages.SysmexAction_DeviceName;

  public static String SysmexAction_ButtonName = ch.elexis.core.l10n.Messages.SysmexAction_ButtonName;
  public static String SysmexAction_ToolTip = ch.elexis.core.l10n.Messages.SysmexAction_ToolTip;

  public static String SysmexAction_LogError_Title = ch.elexis.core.l10n.Messages.SysmexAction_LogError_Title;
  public static String SysmexAction_LogError_Text = ch.elexis.core.l10n.Messages.SysmexAction_LogError_Text;
  public static String SysmexAction_NoPatientMsg = ch.elexis.core.l10n.Messages.SysmexAction_NoPatientMsg;

  public static String SysmexAction_ProbeError_Title = ch.elexis.core.l10n.Messages.SysmexAction_ProbeError_Title;

  public static String SysmexAction_Patient_Title = ch.elexis.core.l10n.Messages.SysmexAction_Patient_Title;
  public static String SysmexAction_Patient_Text = ch.elexis.core.l10n.Messages.SysmexAction_Patient_Text;
  public static String SysmexAction_PatientHeaderString = ch.elexis.core.l10n.Messages.SysmexAction_PatientHeaderString;

  public static String SysmexAction_ResendMsg = ch.elexis.core.l10n.Messages.SysmexAction_ResendMsg;
  public static String SysmexAction_RS232_Error_Title = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Error_Title;
  public static String SysmexAction_RS232_Error_Text = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Error_Text;

  public static String SysmexAction_RS232_Break_Title = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Break_Title;
  public static String SysmexAction_RS232_Break_Text = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Break_Text;

  public static String SysmexAction_RS232_Timeout_Title = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Timeout_Title;
  public static String SysmexAction_RS232_Timeout_Text = ch.elexis.core.l10n.Messages.SysmexAction_RS232_Timeout_Text;
  public static String SysmexAction_WaitMsg = ch.elexis.core.l10n.Messages.SysmexAction_WaitMsg;
  public static String SysmexAction_ErrorTitle = ch.elexis.core.l10n.Messages.SysmexAction_ErrorTitle;
  public static String SysmexAction_WrongDataFormat = ch.elexis.core.l10n.Messages.SysmexAction_WrongDataFormat;

  public static String Preferences_Port = ch.elexis.core.l10n.Messages.Preferences_Port;
  public static String Preferences_Baud = ch.elexis.core.l10n.Messages.Preferences_Baud;
  public static String Preferences_Databits = ch.elexis.core.l10n.Messages.Preferences_Databits;
  public static String Preferences_Parity = ch.elexis.core.l10n.Messages.Preferences_Parity;
  public static String Preferences_Stopbits = ch.elexis.core.l10n.Messages.Preferences_Stopbits;
  public static String Preferences_Timeout = ch.elexis.core.l10n.Messages.Preferences_Timeout;
  public static String Preferences_Backgroundprocess = ch.elexis.core.l10n.Messages.Preferences_Backgroundprocess;
  public static String Preferences_Log = ch.elexis.core.l10n.Messages.Preferences_Log;
  public static String Preferences_Modell = ch.elexis.core.l10n.Messages.Preferences_Modell;
  public static String Preferences_RDW = ch.elexis.core.l10n.Messages.Preferences_RDW;
  public static String Preferences_Verbindung = ch.elexis.core.l10n.Messages.Preferences_Verbindung;
  public static String Sysmex_Probe_ResultatMsg = ch.elexis.core.l10n.Messages.Sysmex_Probe_ResultatMsg;
  public static String Sysmex_Value_LabKuerzel = ch.elexis.core.l10n.Messages.Sysmex_Value_LabKuerzel;
  public static String Sysmex_Value_LabName = ch.elexis.core.l10n.Messages.Sysmex_Value_LabName;
  public static String Sysmex_Value_High = ch.elexis.core.l10n.Messages.Sysmex_Value_High;
  public static String Sysmex_Value_Low = ch.elexis.core.l10n.Messages.Sysmex_Value_Low;
  public static String Sysmex_Value_Error = ch.elexis.core.l10n.Messages.Sysmex_Value_Error;

  static { // load message values from bundle file
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}

