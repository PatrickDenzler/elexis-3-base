package ch.elexis.global_inbox.core.service;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import ch.elexis.core.model.tasks.IIdentifiedRunnable;
import ch.elexis.core.model.tasks.IIdentifiedRunnableFactory;
import ch.elexis.core.services.IAccessControlService;
import ch.elexis.core.services.IModelService;
import ch.elexis.core.services.IVirtualFilesystemService;
import ch.elexis.core.tasks.internal.runnables.DeleteFileIdentifiedRunnable;
import ch.elexis.core.tasks.internal.runnables.LogResultContextIdentifiedRunnable;
import ch.elexis.core.tasks.internal.runnables.RemoveTaskLogEntriesRunnable;
import ch.elexis.core.tasks.model.ITaskService;
import ch.elexis.global_inbox.core.handler.MoveFileIdentifiedRunnable;


@Component(immediate = true)
public class IdentifiedRunnableFactoryImplMover implements IIdentifiedRunnableFactory {

	@Reference
	private ITaskService taskService;

	@Reference
	private IVirtualFilesystemService virtualFilsystemService;

	private IModelService taskModelService;

	@Reference(target = "(" + IModelService.SERVICEMODELNAME + "=ch.elexis.core.tasks.model)")
	private void setModelService(IModelService modelService) {
		taskModelService = modelService;
	}

	@Reference(target = "(" + IModelService.SERVICEMODELNAME + "=ch.elexis.core.tasks.model)")
	public void getModelService(IModelService modelService) {
		taskModelService = modelService;
	}

	@Reference
	private IAccessControlService accessControlService;

	@Activate
	public void activate() {
		taskService.bindIIdentifiedRunnableFactory(this);
	}

	@Deactivate
	public void deactivate() {
		taskService.unbindIIdentifiedRunnableFactory(this);
	}

	@Override
	public List<IIdentifiedRunnable> getProvidedRunnables() {
		List<IIdentifiedRunnable> ret = new ArrayList<>();
		ret.add(new MoveFileIdentifiedRunnable(virtualFilsystemService));
		return ret;
	}

}