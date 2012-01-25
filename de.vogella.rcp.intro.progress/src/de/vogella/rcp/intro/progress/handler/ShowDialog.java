package de.vogella.rcp.intro.progress.handler;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowDialog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(HandlerUtil
				.getActiveShell(event).getShell());
		try {
			dialog.run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) {
					monitor.beginTask("Doing something time consuming here", 10);
					for (int i = 1; i <= 10; i++) {
						if (monitor.isCanceled())
							return;
						monitor.subTask("I'm doing something here " + i);
						sleep(1000);
						monitor.worked(1);
					}
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void sleep(Integer waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (Throwable t) {
			System.out.println("Wait time interrupted");
		}
	}

}
