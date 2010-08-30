package de.vogella.task.changes.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.task.application.views.TaskOverview;
import de.vogella.task.changes.wizard.MyWizard;
import de.vogella.task.dao.MockDao;

public class AddNewTask extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MyWizard wizard = new MyWizard();
		WizardDialog dialog = new WizardDialog(HandlerUtil
				.getActiveShell(event), wizard);
		if (dialog.open() == Window.OK) {
			MockDao.INSTANCE.getTasks().add(wizard.getTask());
			IViewPart view = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().findView(
							TaskOverview.ID);
			((TaskOverview) view).refresh();
		}

		return null;
	}

}
