package de.vogella.rcp.intro.editor.provider;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.vogella.rcp.intro.editor.model.MyModel;

public class MyContentProvider implements IStructuredContentProvider,
		PropertyChangeListener {

	private final Viewer viewer;

	public MyContentProvider(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		MyModel content = (MyModel) inputElement;
		return content.getPersons().toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		viewer.refresh();
	}

}
