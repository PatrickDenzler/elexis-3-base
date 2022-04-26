package at.medevit.elexis.outbox.ui.command;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import at.medevit.elexis.outbox.model.IOutboxElement;
import at.medevit.elexis.outbox.ui.OutboxServiceComponent;
import at.medevit.elexis.outbox.ui.part.provider.IOutboxElementUiProvider;
import at.medevit.elexis.outbox.ui.part.provider.OutboxElementUiExtension;

public class OutboxDeleteHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof StructuredSelection && !((StructuredSelection) selection).isEmpty()) {
			List<?> iOutboxElements = ((StructuredSelection) selection).toList();
			for (Object iOutboxElement : iOutboxElements) {
				if (iOutboxElement instanceof IOutboxElement) {
					OutboxElementUiExtension extension = new OutboxElementUiExtension();
					IOutboxElement el = (IOutboxElement) iOutboxElement;
					IOutboxElementUiProvider provider = extension.getProviderFor(el);
					if (provider != null) {
						provider.delete(el);
					}
					OutboxServiceComponent.get().deleteOutboxElement(el);
				}
			}

		}
		return null;
	}
}
