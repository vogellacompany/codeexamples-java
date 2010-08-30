package de.vogella.rcp.commands.parameterfirst.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class HelloName extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String name = event
				.getParameter("de.vogella.rcp.commands.parameterfirst.commandParameter1");
		MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
				"Hello", "Hello " + name);
		return null;
	}
}
