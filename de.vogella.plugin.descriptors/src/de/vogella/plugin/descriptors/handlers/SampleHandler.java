package de.vogella.plugin.descriptors.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

public class SampleHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Views
		System.out.println("All my View ID's");
		IViewRegistry viewRegistry = PlatformUI.getWorkbench().getViewRegistry();
		IViewDescriptor[] views = viewRegistry.getViews();
		for (IViewDescriptor iViewDescriptor : views) {
			System.out.println(iViewDescriptor.getId());
		}
		return null;
	}
}
