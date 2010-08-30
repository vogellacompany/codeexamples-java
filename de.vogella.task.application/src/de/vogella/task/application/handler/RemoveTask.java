package de.vogella.task.application.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.task.application.views.TaskOverview;
import de.vogella.task.dao.MockDao;
import de.vogella.task.model.ITask;

public class RemoveTask extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView(TaskOverview.ID);
		TaskOverview taskview = (TaskOverview) view;
		IStructuredSelection selection = (IStructuredSelection) taskview
				.getViewer().getSelection();
		if (selection != null) {
			for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
				ITask task = (ITask) iterator.next();
				MockDao.INSTANCE.getTasks().remove(task);
			}
			taskview.refresh();
		} else {
			MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
					"Nothing to delete", "Please select first tasks.");
		}
		return null;
	}
}
