package editortest.editors;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.ui.IEditorInput;

import org.eclipse.ui.IPersistableElement;

public class MyNameEditorInput implements IEditorInput {

	private final String lastname;

	public MyNameEditorInput(String lastname) {

		this.lastname = lastname;

	}

	@Override
	public boolean exists() {

		// TODO Auto-generated method stub

		return false;

	}

	@Override
	public ImageDescriptor getImageDescriptor() {

		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public String getName() {

		return lastname;

	}

	@Override
	public IPersistableElement getPersistable() {

		// TODO Auto-generated method stub

		return null;

	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * 
	 * 
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 * 
	 */

	@Override
	public String getToolTipText() {

		return "My Tool Tip";

	}

	@Override
	public Object getAdapter(Class adapter) {

		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public boolean equals(Object obj) {

		if (super.equals(obj)) {

			return true;

		}

		if (obj instanceof MyNameEditorInput) {

			return lastname.equals(((MyNameEditorInput) obj).getName());

		}

		return false;

	}

	@Override
	public int hashCode() {

		return lastname.hashCode();

	}

}