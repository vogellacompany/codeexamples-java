package de.vogella.plugin.filterpackageexplorer;

/*******************************************************************************
 * Copyright (c) 2008 Patrick "Frz" Huy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Find the original source code here https://github.com/FrzMe/eclipse-project-exploring-plugins
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.SearchPattern;

@SuppressWarnings("restriction")
public class FilterContentProvider extends PackageExplorerContentProvider {
	private PackageExplorerContentProvider delegate;
	private SearchPattern pattern = new SearchPattern();

	public FilterContentProvider(PackageExplorerContentProvider delegate) {
		super(delegate.getProvideMembers());
		this.delegate = delegate;
	}

	public void setFilter(String filter) {
		pattern.setPattern(filter);
	}

	@Override
	public void dispose() {
		delegate.dispose();
	}

	@Override
	public void elementChanged(ElementChangedEvent event) {
		delegate.elementChanged(event);
	}

	@Override
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return filterProjects(delegate.getChildren(parentElement));
	}

	@Override
	public Object[] getElements(Object parent) {
		return filterProjects(delegate.getElements(parent));
	}

	private Object[] filterProjects(Object[] objects) {
		List<Object> ret = new ArrayList<Object>(objects.length);
		for (Object o : objects) {
			if (o instanceof IProject) {
				if (matchesFilter((IProject) o)) {
					ret.add(o);
				}
			} else if (o instanceof IJavaProject) {
				if (matchesFilter((IJavaProject) o)) {
					ret.add(o);
				}
			} else {
				ret.add(o);
			}
		}
		return ret.toArray();
	}

	private String getProjectName(IProject pj) {
		if (pj.isOpen()) {
			try {
				return pj.getDescription().getName();
			} catch (CoreException e) {
				// Activator.log(e);
			}
		}
		return pj.getName();
	}

	private boolean matchesFilter(IProject p) {
		return pattern.matches(getProjectName(p));
	}

	private boolean matchesFilter(IJavaProject jp) {
		return pattern.matches(jp.getElementName());
	}

	@Override
	public Object getHierarchicalPackageParent(IPackageFragment child) {
		return delegate.getHierarchicalPackageParent(child);
	}

	@Override
	public Object getParent(Object element) {
		return delegate.getParent(element);
	}

	@Override
	public boolean getProvideMembers() {
		return delegate.getProvideMembers();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean getProvideWorkingCopy() {
		return delegate.getProvideWorkingCopy();
	}

	@Override
	public boolean hasChildren(Object element) {
		return delegate.hasChildren(element);
	}

	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		delegate.inputChanged(viewer, oldInput, newInput);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		delegate.propertyChange(event);
	}

	@Override
	public boolean providesWorkingCopies() {
		return delegate.providesWorkingCopies();
	}

	@Override
	public void runPendingUpdates() {
		delegate.runPendingUpdates();
	}

	@Override
	public void setIsFlatLayout(boolean state) {
		delegate.setIsFlatLayout(state);
	}

	@Override
	public void setProvideMembers(boolean b) {
		delegate.setProvideMembers(b);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setProvideWorkingCopy(boolean b) {
		delegate.setProvideWorkingCopy(b);
	}

	@Override
	public void setShowLibrariesNode(boolean state) {
		delegate.setShowLibrariesNode(state);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}
}
