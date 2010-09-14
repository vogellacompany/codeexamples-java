package de.vogella.rcp.dialogs.jface;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenSelectionDialog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, new LabelProvider());
		dialog.setElements(new String[] { "Linux", "Mac", "Windows" });
		dialog.setTitle("Which operating system are you using");
		// User pressed cancel
		if (dialog.open() != Window.OK) {
			return false;
		}
		Object[] result = dialog.getResult();
		for (Object s : result) {
			System.out.println(s.toString());
		}
		return null;
	}

}
