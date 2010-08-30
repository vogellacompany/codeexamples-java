package de.vogella.jdt.codeanalysis.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.jdt.codeanalysis.analysis.CodeAnalysis;
import de.vogella.jdt.codeanalysis.model.MethodInformation;
import de.vogella.jdt.codeanalysis.views.ResultView;

public class CalculateUsage extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		if (selection == null || selection.getFirstElement() == null) {
			// Nothing selected, do nothing
			MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
					"Information", "Please select a project");
			return null;
		}
		final Object firstElement = selection.getFirstElement();
		if (!(firstElement instanceof IJavaProject)) {
			return null;
		}
		
		final IJavaProject project = (IJavaProject) firstElement;

		try {
			if (!project.isOpen()
					|| !(project.getProject()
							.hasNature("org.eclipse.jdt.core.javanature"))) {
				MessageDialog.openInformation(
						HandlerUtil.getActiveShell(event), "Information",
						"Only works for open Java Projects");
				return null;
			}
		} catch (CoreException e1) {
			return null;
		}

		Job job = new Job("Calculate Usage of methods") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final List<MethodInformation> calculate = CodeAnalysis
						.calculate(project);
				// Open view in the UI thread
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						try {
							final ResultView findView = (ResultView) HandlerUtil
									.getActiveWorkbenchWindow(event)
									.getActivePage().showView(ResultView.ID);
							findView.setInput(calculate);
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}

				});
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();

		return null;
	}
}
