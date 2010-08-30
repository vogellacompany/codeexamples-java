package de.vogella.rap.theming;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Opens an &quot;About RAP&quot; message dialog.
 */
public class AboutAction extends Action {
	
	private final IWorkbenchWindow window;
	
	public AboutAction(IWorkbenchWindow window) {
		super("About");
		setId(this.getClass().getName());
		this.window = window;
	}
	
	public void run() {
		if(window != null) {	
			String title = "About RAP";
			String msg =   "RAP Mail template created by PDE.\n\n"
			             + "You can learn more about RAP at:\n\n"
			             + "http://www.eclipse.org/rap";
			MessageDialog.openInformation( window.getShell(), title, msg ); 
		}
	}
	
}
