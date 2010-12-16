package de.vogella.rcp.intro.forms.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.handlers.HandlerUtil;

public class HelloHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageBox box = new MessageBox(HandlerUtil.getActiveShell(event),
				SWT.OK);
		box.open();
		return null;
	}

}
