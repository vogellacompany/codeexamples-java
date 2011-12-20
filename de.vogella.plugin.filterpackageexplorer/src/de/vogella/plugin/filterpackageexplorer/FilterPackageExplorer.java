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

import org.eclipse.jdt.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerContentProvider;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.plugin.AbstractUIPlugin;

@SuppressWarnings("restriction")
public class FilterPackageExplorer extends PackageExplorerPart {
	private FilterContentProvider fcp;
	private Text filtertext;
	private ToolBarManager filterToolBar;

	private static final String CLEAR_ICON = "cc.frz.ecl.filterpackageexplorer.CLEAR_ICON"; //$NON-NLS-1$
	private static final String DCLEAR_ICON = "cc.frz.ecl.filterpackageexplorer.DCLEAR_ICON"; //$NON-NLS-1$

	static {
		ImageDescriptor descriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(PlatformUI.PLUGIN_ID,
						"$nl$/icons/full/etool16/clear_co.gif"); //$NON-NLS-1$
		if (descriptor != null) {
			JFaceResources.getImageRegistry().put(CLEAR_ICON, descriptor);
		}
		descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
				PlatformUI.PLUGIN_ID, "$nl$/icons/full/dtool16/clear_co.gif"); //$NON-NLS-1$
		if (descriptor != null) {
			JFaceResources.getImageRegistry().put(DCLEAR_ICON, descriptor);
		}
	}

	public FilterPackageExplorer() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite newParent = new Composite(parent, 0);
		GridLayout layout = new GridLayout(2, false);
		newParent.setBackground(Display.getDefault().getSystemColor(
				SWT.COLOR_WHITE));
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		newParent.setLayout(layout);

		filtertext = new Text(newParent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH);
		filtertext.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));

		filtertext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateTree();
			}
		});
		IWorkbench workbench = PlatformUI.getWorkbench();
		final IBindingService bindingService = (IBindingService) workbench
				.getService(IBindingService.class);
		if (bindingService != null) {
			filtertext.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					bindingService.setKeyFilterEnabled(false);
				}

				public void focusLost(FocusEvent e) {
					bindingService.setKeyFilterEnabled(true);
				}
			});
		}

		TextFieldNavigationHandler.install(filtertext);
		createClearText(newParent);

		Composite treeContainer = new Composite(newParent, 0);
		treeContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));
		treeContainer.setLayout(new FillLayout());

		super.createPartControl(treeContainer);
	}

	private void updateTree() {
		fcp.setFilter("*" + filtertext.getText());
		getTreeViewer().refresh();
	}

	/**
	 * Create the button that clears the text.
	 * 
	 * @param parent
	 *            parent <code>Composite</code> of toolbar button
	 */
	private void createClearText(Composite parent) {
		filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
		ToolBar control = filterToolBar.createControl(parent);
		control.setBackground(Display.getDefault().getSystemColor(
				SWT.COLOR_WHITE));

		IAction clearTextAction = new Action("", IAction.AS_PUSH_BUTTON) {//$NON-NLS-1$
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				filtertext.setText("");
				updateTree();
			}
		};

		clearTextAction
				.setToolTipText(WorkbenchMessages.FilteredTree_ClearToolTip);
		clearTextAction.setImageDescriptor(JFaceResources.getImageRegistry()
				.getDescriptor(CLEAR_ICON));
		clearTextAction.setDisabledImageDescriptor(JFaceResources
				.getImageRegistry().getDescriptor(DCLEAR_ICON));
		filterToolBar.add(clearTextAction);

		filterToolBar.update(false);
	}

	@Override
	public void rootModeChanged(int newMode) {
		super.rootModeChanged(newMode);
	}

	@Override
	public IViewSite getViewSite() {
		Exception e = new Exception();
		e.fillInStackTrace();
		StackTraceElement[] stackTrace = e.getStackTrace();
		if (stackTrace[1].getClassName().equals(
				"org.eclipse.jdt.ui.actions.CustomFiltersActionGroup")) {
			return new HackViewSite("org.eclipse.jdt.ui.PackageExplorer",
					super.getViewSite());
		}
		return super.getViewSite();
	}

	@Override
	public PackageExplorerContentProvider createContentProvider() {
		PackageExplorerContentProvider contentProvider = super
				.createContentProvider();
		fcp = new FilterContentProvider(contentProvider);
		fcp.setFilter(filtertext.getText());
		return fcp;
	}

	@Override
	public void selectAndReveal(Object element) {
		super.selectAndReveal(element);
	}

	@Override
	public void selectReveal(ISelection selection) {
		super.selectReveal(selection);
	}
}