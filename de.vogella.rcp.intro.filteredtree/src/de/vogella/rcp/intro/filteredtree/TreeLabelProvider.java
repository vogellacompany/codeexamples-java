package de.vogella.rcp.intro.filteredtree;

import org.eclipse.jface.viewers.LabelProvider;

public class TreeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String name = (String) element;
		return name;
	}
}
