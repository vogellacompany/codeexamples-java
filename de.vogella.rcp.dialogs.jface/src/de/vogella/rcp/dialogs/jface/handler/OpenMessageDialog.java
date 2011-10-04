package de.vogella.rcp.dialogs.jface.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenMessageDialog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

		// Customized MessageDialog with configured buttons
		MessageDialog dialog = new MessageDialog(shell, "My Title", null,
				"My message", MessageDialog.ERROR, new String[] { "First",
						"Second", "Third" }, 0);
		int result = dialog.open();
		System.out.println(result);

		// A few standard message dialog
		MessageDialog.openConfirm(shell, "Confirm", "Please confirm");
		MessageDialog.openError(shell, "Error", "Error occured");
		MessageDialog.openInformation(shell, "Info", "Info for you");
		MessageDialog.openQuestion(shell, "Question", "Really, really?");
		MessageDialog.openWarning(shell, "Warning", "I warn you");

		return null;
	}
}
