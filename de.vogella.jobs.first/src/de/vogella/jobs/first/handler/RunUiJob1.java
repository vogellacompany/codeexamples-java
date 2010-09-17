package de.vogella.jobs.first.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

public class RunUiJob1 extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		UIJob job = new UIJob("First Job") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				for (int i = 0; i < 10; i++) {
					try {
						// We simulate a long running operation here
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Doing something");
				}
				MessageDialog.openInformation(
						HandlerUtil.getActiveShell(event), "Your Popup ",
						"Your job has finished.");

				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();
		return null;
	}

}
