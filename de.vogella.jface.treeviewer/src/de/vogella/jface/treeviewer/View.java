package de.vogella.jface.treeviewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

import de.vogella.jface.treeviewer.model.TodoMockModel;
import de.vogella.jface.treeviewer.provider.TodoContentProvider;
import de.vogella.jface.treeviewer.provider.TodoLabelProvider;

public class View extends ViewPart {
	public static final String ID = "de.vogella.jface.treeviewer.view";
	private TreeViewer viewer;

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TodoContentProvider());
		viewer.setLabelProvider(new TodoLabelProvider());
		// Expand the tree
		viewer.setAutoExpandLevel(2);
		// Provide the input to the ContentProvider
		viewer.setInput(new TodoMockModel());

		Tree tree = (Tree) viewer.getControl();
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				if (item.getItemCount() > 0) {
					item.setExpanded(!item.getExpanded());
				}
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}