package de.vogella.rcp.commands.parametersecond.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class SayHello extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String name = event
				.getParameter("de.vogella.rcp.commands.parametersecond.commandParameter1");
		MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
				"Hello", "Hello " + name);
		return null;
	}

}
