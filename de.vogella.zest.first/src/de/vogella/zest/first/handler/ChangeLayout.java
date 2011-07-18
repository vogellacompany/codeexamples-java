package de.vogella.zest.first.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.zest.first.View;

public class ChangeLayout extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IViewPart findView = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().findView("de.vogella.zest.first.view");
		View view = (View) findView;
		view.setLayoutManager();
		return null;
	}

}

