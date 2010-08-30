package de.vogella.jface.treeviewer.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.vogella.jface.treeviewer.model.Category;
import de.vogella.jface.treeviewer.model.Todo;

public class TodoLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Category) {
			Category category = (Category) element;
			return category.getName();
		}
		return ((Todo) element).getSummary();
	}
}
