package de.vogella.plugin.adapter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import de.vogella.plugin.adapter.model.Todo;

public class TodoWorkbenchAdapter implements IWorkbenchAdapter {


	
	@Override
	public Object[] getChildren(Object o) {
		String[] values = new String[2];
		Todo todo = (Todo) o;
		values[0]= todo.getSummary();
		values[1]= todo.getDescription();
		return values;
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return null;
	}

	@Override
	public String getLabel(Object o) {
		return null;
	}

	@Override
	public Object getParent(Object o) {
		return null;
	}

}
