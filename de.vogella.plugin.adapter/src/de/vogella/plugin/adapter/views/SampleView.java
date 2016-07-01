package de.vogella.plugin.adapter.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.vogella.plugin.adapter.model.Todo;

public class SampleView {

	private TableViewer viewer;

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			Todo todo = (Todo) obj;
			return todo.getSummary();
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@PostConstruct
	public void createPartControl(Composite parent, ESelectionService selectionService) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(event.getSelection());
			}
		});

		viewer.setInput(getElements());
	}

	// Build up a simple data model
	private List<Todo> getElements() {
		List<Todo> todos = new ArrayList<>();
		Todo todo = new Todo();
		todo.setSummary("First Todo");
		todo.setDescription("A very good description");
		todos.add(todo);
		todo = new Todo();
		todo.setSummary("Second Todo");
		todo.setDescription("Second super description");
		todos.add(todo);

		return todos;
	}
}
