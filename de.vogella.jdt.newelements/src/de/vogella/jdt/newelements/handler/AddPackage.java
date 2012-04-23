package de.vogella.jdt.newelements.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class AddPackage extends AbstractHandler {

	private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			try {
				// Only work on open projects with the Java nature
				if (project.isOpen()
						& project.isNatureEnabled(JavaCore.NATURE_ID)) {
					createPackage(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void createPackage(IProject project) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(project);
		IClasspathEntry[] entries = javaProject.getRawClasspath();
		IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];

		System.arraycopy(entries, 0, newEntries, 0, entries.length);

		// add a new entry using the path to the container
		Path junitPath = new Path("org.eclipse.jdt.junit.JUNIT_CONTAINER/4");
		IClasspathEntry junitEntry = JavaCore.newContainerEntry(junitPath);
		newEntries[entries.length] = JavaCore.newContainerEntry(junitEntry
				.getPath());
		javaProject.setRawClasspath(newEntries, null);
		IFolder folder = project.getFolder("src");
		// folder.create(true, true, null);
		IPackageFragmentRoot srcFolder = javaProject
				.getPackageFragmentRoot(folder);
		String projectName = project.getName();
		// Create a package
		srcFolder.createPackageFragment(projectName, true, null);
	}
}
