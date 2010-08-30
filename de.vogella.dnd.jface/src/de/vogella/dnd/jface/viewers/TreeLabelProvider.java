package de.vogella.dnd.jface.viewers;

import org.eclipse.jface.viewers.LabelProvider;


public class TreeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String s = (String) element; 
		return s;
	}

}
