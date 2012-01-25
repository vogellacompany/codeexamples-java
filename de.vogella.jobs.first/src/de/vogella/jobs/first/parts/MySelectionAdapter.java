package de.vogella.jobs.first.parts;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MySelectionAdapter extends SelectionAdapter {
	private final Shell shell;

	public MySelectionAdapter(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Job job = new Job("First Job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				doLongThing();
				syncWithUi();
				// Use this to open a Shell in the UI thread
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();
	}

	private void doLongThing() {
		for (int i = 0; i < 10; i++) {
			try {
				// We simulate a long running operation here
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Doing something");
		}
	}

	private void syncWithUi() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openInformation(shell, "Your Popup ",
						"Your job has finished.");
			}
		});

	}
}