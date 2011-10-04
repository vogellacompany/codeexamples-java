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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

@SuppressWarnings("deprecation")
public class HackViewSite implements IViewSite {
	private IViewSite delegate;
	private String fakeid;

	public HackViewSite(String fakeid, IViewSite viewSite) {
		this.delegate = viewSite;
		this.fakeid = fakeid;
	}

	public IActionBars getActionBars() {
		return delegate.getActionBars();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		return delegate.getAdapter(adapter);
	}

	public String getId() {
		return fakeid;
	}

	public IKeyBindingService getKeyBindingService() {
		return delegate.getKeyBindingService();
	}

	public IWorkbenchPage getPage() {
		return delegate.getPage();
	}

	public IWorkbenchPart getPart() {
		return delegate.getPart();
	}

	public String getPluginId() {
		return delegate.getPluginId();
	}

	public String getRegisteredName() {
		return delegate.getRegisteredName();
	}

	public String getSecondaryId() {
		return delegate.getSecondaryId();
	}

	public ISelectionProvider getSelectionProvider() {
		return delegate.getSelectionProvider();
	}

	@SuppressWarnings("unchecked")
	public Object getService(Class api) {
		return delegate.getService(api);
	}

	public Shell getShell() {
		return delegate.getShell();
	}

	public IWorkbenchWindow getWorkbenchWindow() {
		return delegate.getWorkbenchWindow();
	}

	@SuppressWarnings("unchecked")
	public boolean hasService(Class api) {
		return delegate.hasService(api);
	}

	public void registerContextMenu(MenuManager menuManager,
			ISelectionProvider selectionProvider) {
		delegate.registerContextMenu(menuManager, selectionProvider);
	}

	public void registerContextMenu(String menuId, MenuManager menuManager,
			ISelectionProvider selectionProvider) {
		delegate.registerContextMenu(menuId, menuManager, selectionProvider);
	}

	public void setSelectionProvider(ISelectionProvider provider) {
		delegate.setSelectionProvider(provider);
	}

}