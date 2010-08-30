package de.vogella.jface.tableviewer.providers;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import de.vogella.jface.tableviewer.model.Person;

public class PersonEditingSupport extends EditingSupport {
	private CellEditor editor;
	private int column;

	public PersonEditingSupport(ColumnViewer viewer, int column) {
		super(viewer);

		String[] gender = new String[2];
		gender[0] = "male";
		gender[1] = "female";

		// Create the correct editor based on the column index
		switch (column) {
		case 2:
			editor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(),
					gender);
			break;
		case 3:
			editor = new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
			break;
		default:
			editor = new TextCellEditor(((TableViewer) viewer).getTable());
		}
		this.column = column;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		Person person = (Person) element;

		switch (this.column) {
		case 0:
			return person.getFirstName();
		case 1:
			return person.getLastName();
		case 2:
			if (person.getGender().equals("male")) {
				return 0;
			}
			return 1;
		case 3:
			return person.isMarried();
		default:
			break;
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		Person pers = (Person) element;

		switch (this.column) {
		case 0:
			pers.setFirstName(String.valueOf(value));
			break;
		case 1:
			pers.setLastName(String.valueOf(value));
			break;
		case 2:
			if (((Integer) value) == 0) {
				pers.setGender("male");
			} else {
				pers.setGender("female");
			}
			break;
		case 3:
			pers.setMarried((Boolean) value);
			break;
		default:
			break;
		}

		getViewer().update(element, null);
	}

}
