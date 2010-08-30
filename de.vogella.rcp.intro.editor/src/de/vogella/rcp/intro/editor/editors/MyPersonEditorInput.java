package de.vogella.rcp.intro.editor.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.vogella.rcp.intro.editor.model.Person;

public class MyPersonEditorInput implements IEditorInput {

	private final Person person;

	public MyPersonEditorInput(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return person.toString();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return person.toString();
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (obj instanceof MyPersonEditorInput) {
			return person.equals(((MyPersonEditorInput) obj).getPerson());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return person.hashCode();
	}
}
