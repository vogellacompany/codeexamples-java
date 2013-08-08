package de.vogella.rcp.intro.dialogs.custom.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

import de.vogella.rcp.intro.dialogs.custom.dialogs.MyTitleAreaDialog;

public class OpenMyDialog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MyTitleAreaDialog dialog = new MyTitleAreaDialog(null);
		dialog.create();
		if (dialog.open() == Window.OK) {
			System.out.println(dialog.getFirstName());
			System.out.println(dialog.getLastName());
		}

		return null;
	}

}
