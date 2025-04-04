package ch.elexis.mednet.webapi.ui.parts;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import ch.elexis.core.model.IDocument;
import ch.elexis.core.model.Identifiable;
import ch.elexis.core.services.holder.StoreToStringServiceHolder;
import ch.elexis.core.ui.icons.Images;
import ch.elexis.mednet.webapi.core.fhir.resources.util.AttachmentsUtil;
import ch.elexis.mednet.webapi.core.messages.Messages;
import ch.elexis.mednet.webapi.ui.handler.DocumentRemovalListener;

public class AttachmentsComposite extends Composite {

	private String attachments;
	private String documents;
	private String postfix;

	private Composite attachmentsParent;

	public AttachmentsComposite(Composite parent, int style) {
		super(parent, style);
		this.setData("org.eclipse.e4.ui.css.CssClassName", "CustomComposite"); //$NON-NLS-1$ //$NON-NLS-2$
		createContent();
	}

	private DocumentRemovalListener removalListener;

	public void setDocumentRemovalListener(DocumentRemovalListener listener) {
		this.removalListener = listener;
	}

	private void createContent() {
		setLayout(new GridLayout(3, false));

		Label dropLabel = new Label(this, SWT.BORDER | SWT.CENTER);
		dropLabel.setText(Messages.AttachmentsComposite_dragFileHere);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd.heightHint = 30;
		dropLabel.setLayoutData(gd);
		Label lbl = new Label(this, SWT.NONE);
		lbl.setText(Messages.AttachmentsComposite_attachment);
		lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		attachmentsParent = new Composite(this, SWT.NONE);
		attachmentsParent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		attachmentsParent.setLayout(new GridLayout(2, false));

		ToolBarManager mgr = new ToolBarManager();
		ToolBar toolbar = mgr.createControl(this);
		toolbar.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));

		final Transfer[] dropTransferTypes = new Transfer[] { FileTransfer.getInstance() };

		DropTarget target = new DropTarget(dropLabel, DND.DROP_COPY);
		target.setTransfer(dropTransferTypes);
		target.addDropListener(new DropTargetAdapter() {

			@Override
			public void dragEnter(DropTargetEvent event) {
				event.detail = DND.DROP_COPY;
			}

			public void drop(DropTargetEvent event) {
				if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
					String[] files = (String[]) event.data;
					Arrays.asList(files).forEach(s -> {
						if (StringUtils.isBlank(getAttachments())) {
							setAttachments(s);
						} else {
							setAttachments(getAttachments() + AttachmentsUtil.ATTACHMENT_DELIMITER + s);
						}
					});
					updateAttachments();
				}
			}
		});
	}

	public String getAttachmentNames(String attachmentAsString) {
		StringBuilder build = new StringBuilder();
		if (attachmentAsString != null) {
			String[] attachments = attachmentAsString.split(",\n"); //$NON-NLS-1$
			for (String f : attachments) {
				if (build.length() > 0) {
					build.append(",\n"); //$NON-NLS-1$
				}
				build.append(Paths.get(f).getFileName());
			}
		}
		return build.toString();
	}

	/**
	 * String containing references to files.
	 *
	 * @param attachments
	 */
	public void setAttachments(String attachments) {
		this.attachments = attachments;
		updateAttachments();
	}

	private void updateAttachments() {
		attachmentsParent.setRedraw(false);

		for (Control control : attachmentsParent.getChildren()) {
			control.dispose();
		}
		if (StringUtils.isNotBlank(attachments)) {
			String[] attachmentsParts = attachments.split(AttachmentsUtil.ATTACHMENT_DELIMITER);
			for (String string : attachmentsParts) {
				Label label = new Label(attachmentsParent, SWT.NONE);
				label.setText(FilenameUtils.getName(string));
				label.setData(string);
				label.setToolTipText(Messages.AttachmentsComposite_openWithDoubleClick);
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						File file = new File((String) label.getData());
						file.setReadOnly();
						Program.launch((String) label.getData());
					}
				});
				label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				Label remove = new Label(attachmentsParent, SWT.NONE);
				remove.setImage(Images.IMG_DELETE.getImage());
				remove.setData(string);
				remove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						List<String> removeParts = Arrays
								.asList(getAttachments().split(AttachmentsUtil.ATTACHMENT_DELIMITER));
						String removedString = removeParts.stream().filter(part -> !part.equals(remove.getData()))
								.collect(Collectors.joining(AttachmentsUtil.ATTACHMENT_DELIMITER));
						setAttachments(removedString);
					}
				});
				remove.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
			}
		}
		if (StringUtils.isNotBlank(documents)) {
			String[] documentsParts = documents.split(AttachmentsUtil.ATTACHMENT_DELIMITER);
			for (String string : documentsParts) {
				Label label = new Label(attachmentsParent, SWT.NONE);
				String tmpFile = AttachmentsUtil.toAttachment(string);
				if (!tmpFile.toLowerCase().endsWith(".pdf")) { //$NON-NLS-1$
					MessageDialog.openWarning(getShell(), Messages.AttachmentsComposite_warning,
							Messages.AttachmentsComposite_document + FilenameUtils.getName(tmpFile)
									+ Messages.AttachmentsComposite_cannotBeConverted);
				}
				label.setText(FilenameUtils.getName(tmpFile));
				label.setData(string);
				label.setToolTipText(Messages.AttachmentsComposite_openWithDoubleClickAgain);
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						File file = new File(tmpFile);
						file.setReadOnly();
						Program.launch(tmpFile);
					}
				});
				label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				Label remove = new Label(attachmentsParent, SWT.NONE);
				remove.setImage(Images.IMG_DELETE.getImage());
				remove.setData(string);
				remove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						String data = (String) remove.getData();
						List<String> removeParts = Arrays
								.asList(getDocuments().split(AttachmentsUtil.ATTACHMENT_DELIMITER));
						String removedString = removeParts.stream().filter(part -> !part.equals(data))
								.collect(Collectors.joining(AttachmentsUtil.ATTACHMENT_DELIMITER));
						setDocuments(removedString);
						Optional<Identifiable> loaded = StoreToStringServiceHolder.get().loadFromString(data);
						if (loaded.isPresent() && loaded.get() instanceof IDocument) {
							IDocument removedDocument = (IDocument) loaded.get();
							if (removalListener != null) {
								removalListener.documentRemoved(removedDocument);
							}
						}
					}
				});
				remove.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
			}
		}
		attachmentsParent.setRedraw(true);
		getParent().layout(true, true);
	}

	public void addDocument(IDocument document) {
		if (document != null) {
			if (StringUtils.isBlank(documents)) {
				documents = AttachmentsUtil.getDocumentsString(Collections.singletonList(document));
			} else {
				documents += AttachmentsUtil.ATTACHMENT_DELIMITER
						+ AttachmentsUtil.getDocumentsString(Collections.singletonList(document));
			}
		}
		updateAttachments();
	}

	/**
	 * String containing references to {@link IDocument}s.
	 *
	 * @param documents
	 */
	public void setDocuments(String documents) {
		this.documents = documents;
		updateAttachments();
	}

	public String getAttachments() {
		return attachments;
	}

	public String getDocuments() {
		return documents;
	}

	public void setPostfix(String text) {
		this.postfix = text;
	}

	public String getPostfix() {
		return postfix;
	}
}
