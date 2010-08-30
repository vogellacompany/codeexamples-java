
package editortest.contentProvider;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mydomain.MyModel;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class MyContentProvider implements IStructuredContentProvider,
		PropertyChangeListener {

	private MyModel content;
	private final Viewer viewer;

	public MyContentProvider(Viewer viewer) {
		this.viewer = viewer;
		content = new MyModel();
		content.addChangeListener(this);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return content.getPersons().toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		viewer.refresh();
	}

}
