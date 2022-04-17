/*******************************************************************************
 * Copyright (c) 2005-2006, G. Weirich and Elexis
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    G. Weirich - initial implementation
 *    
 *    $Id: Messwert.java 1185 2006-10-29 15:29:30Z rgw_ch $
 *******************************************************************************/

package ch.elexis.base.befunde;


import org.eclipse.osgi.util.NLS;
public class Messages{
  public static final String BUNDLE_NAME = "ch.elexis.base.befunde.messages"; //$NON-NLS-1$
    public static String BefundePrefs_enterRenameMessage = ch.elexis.core.l10n.Messages.BefundePrefs_enterRenameMessage;
    public static String BefundePrefs_enterRenameCaption = ch.elexis.core.l10n.Messages.BefundePrefs_enterRenameCaption;
    public static String ACLContributor_addMesswertACLName = ch.elexis.core.l10n.Messages.ACLContributor_addMesswertACLName;
    public static String ACLContributor_messwertACLName = ch.elexis.core.l10n.Messages.ACLContributor_messwertACLName;
    public static String ACLContributor_messwertRubrikACLName = ch.elexis.core.l10n.Messages.ACLContributor_messwertRubrikACLName;
    public static String BefundePrefs_add = ch.elexis.core.l10n.Messages.BefundePrefs_add;
    public static String BefundePrefs_deleteText = ch.elexis.core.l10n.Messages.BefundePrefs_deleteText;
    public static String BefundePrefs_renameFinding = ch.elexis.core.l10n.Messages.BefundePrefs_renameFinding;
    public static String BefundePrefs_enterNameCaption = ch.elexis.core.l10n.Messages.BefundePrefs_enterNameCaption;
    public static String BefundePrefs_enterNameMessage = ch.elexis.core.l10n.Messages.BefundePrefs_enterNameMessage;
    public static String BefundePrefs_dotEndingNameNotAllowed = ch.elexis.core.l10n.Messages.BefundePrefs_dotEndingNameNotAllowed;
    public static String DataAccessor_0 = ch.elexis.core.l10n.Messages.DataAccessor_0;
    public static String DataAccessor_data = ch.elexis.core.l10n.Messages.DataAccessor_data;
    public static String DataAccessor_dataInBefundePlugin = ch.elexis.core.l10n.Messages.DataAccessor_dataInBefundePlugin;
    public static String DataAccessor_dateExpected = ch.elexis.core.l10n.Messages.DataAccessor_dateExpected;
    public static String DataAccessor_invalidFieldIndex = ch.elexis.core.l10n.Messages.DataAccessor_invalidFieldIndex;
    public static String DataAccessor_invalidFieldName = ch.elexis.core.l10n.Messages.DataAccessor_invalidFieldName;
    public static String DataAccessor_invalidParameter = ch.elexis.core.l10n.Messages.DataAccessor_invalidParameter;
    public static String DataAccessor_notFound = ch.elexis.core.l10n.Messages.DataAccessor_notFound;
    public static String DataAccessor_first = ch.elexis.core.l10n.Messages.DataAccessor_first;
    public static String DataAccessor_last = ch.elexis.core.l10n.Messages.DataAccessor_last;
    public static String DataAccessor_date = ch.elexis.core.l10n.Messages.DataAccessor_date;
    public static String DataAccessor_all = ch.elexis.core.l10n.Messages.DataAccessor_all;
    public static String EditFindingDialog_captionBefundEditDlg = ch.elexis.core.l10n.Messages.EditFindingDialog_captionBefundEditDlg;
    public static String EditFindingDialog_enterTextForBefund = ch.elexis.core.l10n.Messages.EditFindingDialog_enterTextForBefund;
    public static String EditFindingDialog_noPatientSelected = ch.elexis.core.l10n.Messages.EditFindingDialog_noPatientSelected;
    public static String FindingsView_addNewMeasure = ch.elexis.core.l10n.Messages.FindingsView_addNewMeasure;
    public static String FindingsView_deleteActionCaption = ch.elexis.core.l10n.Messages.FindingsView_deleteActionCaption;
    public static String FindingsView_deleteActionToolTip = ch.elexis.core.l10n.Messages.FindingsView_deleteActionToolTip;
    public static String FindingsView_deleteConfirmCaption = ch.elexis.core.l10n.Messages.FindingsView_deleteConfirmCaption;
    public static String FindingsView_deleteConfirmMessage = ch.elexis.core.l10n.Messages.FindingsView_deleteConfirmMessage;
    public static String FindingsView_editActionCaption = ch.elexis.core.l10n.Messages.FindingsView_editActionCaption;
    public static String FindingsView_editActionToolTip = ch.elexis.core.l10n.Messages.FindingsView_editActionToolTip;
    public static String FindingsView_noPatientSelected = ch.elexis.core.l10n.Messages.FindingsView_noPatientSelected;
    public static String FindingsView_printActionCaptiob = ch.elexis.core.l10n.Messages.FindingsView_printActionCaptiob;
    public static String FindingsView_printActionMessage = ch.elexis.core.l10n.Messages.FindingsView_printActionMessage;
    public static String Messwert_couldNotCreateTable = ch.elexis.core.l10n.Messages.Messwert_couldNotCreateTable;
    public static String Messwert_valuesError = ch.elexis.core.l10n.Messages.Messwert_valuesError;
    public static String MesswerteView_date = ch.elexis.core.l10n.Messages.MesswerteView_date;
    public static String MesswerteView_enterNewEntry = ch.elexis.core.l10n.Messages.MesswerteView_enterNewEntry;
    public static String MesswerteView_enterNewValue = ch.elexis.core.l10n.Messages.MesswerteView_enterNewValue;
    public static String MesswerteView_new = ch.elexis.core.l10n.Messages.MesswerteView_new;
    public static String MesswerteView_noPatSelected = ch.elexis.core.l10n.Messages.MesswerteView_noPatSelected;
    public static String PrefsPage_enterCalculationForThis = ch.elexis.core.l10n.Messages.PrefsPage_enterCalculationForThis;
    public static String PrefsPage_multilineCaption = ch.elexis.core.l10n.Messages.PrefsPage_multilineCaption;
    public static String PrefsPage_warningConfirmMessage = ch.elexis.core.l10n.Messages.PrefsPage_warningConfirmMessage;
    public static String PrefsPage_warningNotUndoableCaption = ch.elexis.core.l10n.Messages.PrefsPage_warningNotUndoableCaption;
    public static String PrefsPage_warningConfirmRename = ch.elexis.core.l10n.Messages.PrefsPage_warningConfirmRename;
    public static String PrintFindingsDialog_messwerteCaption = ch.elexis.core.l10n.Messages.PrintFindingsDialog_messwerteCaption;
    public static String PrintFindingsDialog_printMesswerteMessage = ch.elexis.core.l10n.Messages.PrintFindingsDialog_printMesswerteMessage;
    public static String PrintFindingsDialog_printMesswerteTitle = ch.elexis.core.l10n.Messages.PrintFindingsDialog_printMesswerteTitle;

}

