package de.vogella.plugin.markers.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddMarkerHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveSite(event).getSelectionProvider().getSelection();
		if (selection == null) {
			return null;
		}
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof IJavaProject) {
			IJavaProject type = (IJavaProject) firstElement;
			writeMarkers(type);

		}
		return null;
	}

	private void writeMarkers(IJavaProject type) {
		try {
			IResource resource = type.getUnderlyingResource();
			IMarker marker = resource.createMarker(IMarker.TASK);
			marker.setAttribute(IMarker.MESSAGE, "This a a task");
			marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
