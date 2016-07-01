package de.vogella.plugin.markers.handler;

import javax.inject.Named;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;


public class AddMarkerHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection, Adapter adapter) {

		if (selection == null || selection.isEmpty()) {
			return;
		}

		Object firstElement = selection.getFirstElement();
		IResource resource = adapter.adapt(firstElement, IResource.class);

		if (resource != null) {
			writeMarkers(resource);
		}

	}

	private void writeMarkers(IResource resource) {
		try {
			IMarker marker = resource.createMarker(IMarker.TASK);
			marker.setAttribute(IMarker.MESSAGE, "This is a task");
			marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
