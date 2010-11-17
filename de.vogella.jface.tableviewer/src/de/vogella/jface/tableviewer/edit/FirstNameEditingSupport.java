package de.vogella.jface.tableviewer.edit;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import de.vogella.jface.tableviewer.model.Person;

public class FirstNameEditingSupport extends EditingSupport {

	private final TableViewer viewer;

	public FirstNameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(viewer.getTable());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return ((Person) element).getFirstName();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((Person) element).setFirstName(String.valueOf(value));
		viewer.refresh();
	}
}
