package de.vogella.adapter.example.adapters;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import de.vogella.adapter.example.model.Person;

public class PersonAdapter implements IPropertySource {

	private final Person person;

	public PersonAdapter(Person person) {
		this.person = person;

	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor("name", "Name"),
				new TextPropertyDescriptor("street", "Street") };
	}

	@Override
	public Object getPropertyValue(Object id) {
		if ("name".equals(id)) {
			return person.getName();
		}
		if ("street".equals(id)) {
			return person.getName();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if ("name".equals(id)) {
			person.setName((String) value);
			if ("street".equals(id)) {
				person.setStreet((String) value);
			}
		}
	}
}
