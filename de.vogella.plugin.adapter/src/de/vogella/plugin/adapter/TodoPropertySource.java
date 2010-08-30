package de.vogella.plugin.adapter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import de.vogella.plugin.adapter.model.Todo;

public class TodoPropertySource implements IPropertySource {

	private final Todo todo;

	public TodoPropertySource(Todo todo) {
		this.todo = todo;
	}


	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {

		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor("summary", "Summary"),
				new TextPropertyDescriptor("description", "Description") };
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals("summary")) {
			return todo.getSummary();
		}
		if (id.equals("description")) {
			return todo.getDescription();
		}
		return null;
	}

	@Override
	public void resetPropertyValue(Object id) {

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		String s = (String) value;
		if (id.equals("summary")) {
			todo.setSummary(s);
		}
		if (id.equals("description")) {
			todo.setDescription(s);
		}
	}

}
