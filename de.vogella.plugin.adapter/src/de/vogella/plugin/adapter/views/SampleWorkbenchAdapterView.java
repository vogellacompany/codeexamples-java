package de.vogella.plugin.adapter.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import de.vogella.plugin.adapter.model.Todo;

public class SampleWorkbenchAdapterView {

	private TableViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@PostConstruct
	public void createPartControl(Composite parent, ESelectionService selectionService) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ArrayContentProvider());

		// make use of the DelegatingStyledCellLabelProvider with the WorkbenchLabelProvider as IStyledLabelProvider implementation 
		viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new WorkbenchLabelProvider()));
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
		todo.setDone(true);
		todos.add(todo);
		todo = new Todo();
		todo.setSummary("Second Todo");
		todo.setDescription("Second super description");
		todos.add(todo);

		return todos;
	}
}
