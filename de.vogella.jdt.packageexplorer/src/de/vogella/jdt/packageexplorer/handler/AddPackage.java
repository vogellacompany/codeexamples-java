package de.vogella.jdt.packageexplorer.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddPackage extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof IJavaProject) {
			IJavaProject javaProject = (IJavaProject) firstElement;
			try {
				IFolder folder = javaProject.getProject().getFolder("src");
				// folder.create(true, true, null);
				IPackageFragmentRoot srcFolder = javaProject
						.getPackageFragmentRoot(folder);
				srcFolder.createPackageFragment(javaProject.getProject()
						.getName(), true, null);
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
