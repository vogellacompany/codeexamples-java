package de.vogella.rcp.intro.workbench.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.rcp.intro.workbench.View;

public class InteractHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage page = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage();
		IViewReference[] views = page.getViewReferences();
		for (IViewReference view : views) {
			page.hideView(view);
		}
		try {
			page.showView(View.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}
}
