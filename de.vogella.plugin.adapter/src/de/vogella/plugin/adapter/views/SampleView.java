package de.vogella.plugin.adapter.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.vogella.plugin.adapter.model.Todo;

public class SampleView extends ViewPart {
	public static final String ID = "de.vogella.plugin.adapter.views.SampleView";

	private TableViewer viewer;

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			Todo todo = (Todo) obj;
			return todo.getSummary();
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		getSite().setSelectionProvider(viewer);
		viewer.setInput(getElements());

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// Build up a simple data model
	private Todo[] getElements() {
		Todo[] todos = new Todo[2];
		Todo todo = new Todo();
		todo.setSummary("First Todo");
		todo.setDescription("A very good description");
		todos[0] = todo;
		todo = new Todo();
		todo.setSummary("Second Todo");
		todo.setDescription("Second super description");
		todos[1] = todo;
		return todos;
	}
}
