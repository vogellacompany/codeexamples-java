package de.vogella.plugin.openshell.handler;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenShellHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("vogella open shell called");
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		Object firstElement = selection.getFirstElement();
		System.out.println(firstElement.getClass());
		if (firstElement instanceof IFolder) {
			IFolder folder = (IFolder) firstElement;
			URI locationURI = folder.getLocationURI();
			File file = new File(locationURI);

			try {
				Runtime.getRuntime().exec("gnome-terminal", null, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
