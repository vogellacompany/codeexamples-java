package testkeybinding;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class HelloAction implements IWorkbenchWindowActionDelegate {

	private Shell shell;

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		shell = window.getShell();

	}

	@Override
	public void run(IAction action) {
		MessageDialog.openInformation(shell, "Information",
				"Imagine here a powerful wizard with lots of graphics!");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
