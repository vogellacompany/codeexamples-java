package de.vogella.jobs.first.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class RunJob1 extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		Job job = new Job("First Job"){
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				for (int i=0; i<10; i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Doing something");
				}
				HandlerUtil.getActiveShell(event).getDisplay().getDefault().asyncExec(new Runnable() {
		               public void run() {
		            	   MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Info", "Info for you");
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
