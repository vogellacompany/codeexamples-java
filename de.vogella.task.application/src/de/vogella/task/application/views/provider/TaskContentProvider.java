package de.vogella.task.application.views.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.vogella.task.model.ITask;

public class TaskContentProvider implements IStructuredContentProvider {

	private List<ITask> tasks;

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		this.tasks = (List<ITask>) inputElement;
		return tasks.toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Could deregister listeners to model here
	}

}
