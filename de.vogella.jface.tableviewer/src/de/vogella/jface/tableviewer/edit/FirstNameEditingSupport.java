package de.vogella.jface.tableviewer.edit;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellEditorValidator;
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
		final TextCellEditor editor = new TextCellEditor(viewer.getTable());
		editor.setValidator(new ICellEditorValidator() {
			@Override
			public String isValid(Object value) {
				System.out.println("Testing");
				return "No that is not ok";
				// return null;
			}
		});
		editor.addListener(new ICellEditorListener() {

			@Override
			public void editorValueChanged(boolean oldValidState,
					boolean newValidState) {
				if (!newValidState) {
					MessageDialog.openError(viewer.getControl().getShell(),
							"No did not work", "Question");
				}

				System.out.println(oldValidState);
				System.out.println(newValidState);
				System.out.println("Changed");
			}

			@Override
			public void cancelEditor() {
				System.out.println("No");
			}

			@Override
			public void applyEditorValue() {
				System.out.println("No");
			}
		});
		return editor;
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
		viewer.update(element, null);
	}
}
