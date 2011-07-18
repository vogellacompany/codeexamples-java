package de.vogella.zest.jface.zestviewer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.vogella.zest.jface.model.MyNode;

public class NodeFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (element instanceof MyNode) {
			MyNode node = (MyNode) element;
			return true;
			// return node.getName().toLowerCase().contains("a");

		}
		return true;
	}

}
