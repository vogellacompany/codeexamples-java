package de.vogella.task.application.views.editing;

import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import de.vogella.task.model.ITask;
import de.vogella.task.model.Priority;

public class TaskEditingSupport extends EditingSupport {

	private boolean editable = false;
	private CellEditor editor;
	private final int column;
	private Map<Integer, Priority> priorityMap;

	public TaskEditingSupport(int column, TableViewer viewer) {
		super(viewer);
		this.column = column;

		if (column == 2) {
			editable = true;
		}
		// String[] priority = getPrioValues();
		// String[] status = getStatusValues();
		// editor = new ComboBoxCellEditor(viewer.getTable(), status);
		editor = new TextCellEditor(viewer.getTable());
		// }

	}

	// private String[] getPrioValues() {
	// priorityMap = new HashMap<Integer, Priority>();
	// Priority[] priority = Priority.values();
	// String[] a = new String[priority.length];
	// for (int i = 0; i < priority.length; i++) {
	// a[i] = priority[i].toString();
	// priorityMap.put(i, priority[i]);
	// }
	// return a;
	// }

	@Override
	protected boolean canEdit(Object element) {
		return editable;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		ITask task = (ITask) element;
		if (column == 2) {
			return task.getSummary();
		}
		// switch (column) {
		// case 0:
		// return "";// String.valueOf(task.getId());
		// case 1:
		// return task.getSummary();
		// case 2:
		// return convertPrioToInt(task);
		// case 3:
		// return 0; // convertStatusToInt(task);
		// }
		throw new RuntimeException("Should not happen");

	}

	// private int convertStatusToInt(ITask task) {
	// switch (task.getStatus()) {
	// case NEW:
	// return 1;
	// case STARTED:
	// return 2;
	// case DONE:
	// return 3;
	// }
	// throw new RuntimeException("Not possible");
	// }
	//
	// private int convertPrioToInt(ITask task) {
	// for (int index : priorityMap.keySet()) {
	// if (task.getPriority().equals(priorityMap.get(index))) {
	// return index;
	// }
	// }
	// throw new RuntimeException("Not possible");
	// }
	//
	@Override
	protected void setValue(Object element, Object value) {
		ITask task = (ITask) element;
		switch (this.column) {
		case 2:
			task.setSummary(String.valueOf(value));
			break;
		// case 2:
		// task.setPriority(convertIntToPrio(value));
		// break;
		// case 3:
		// task.setStatus(Status.NEW);
		// break;
		// default:
		// break;
		}
		//
		getViewer().update(element, null);
		//
	}
	//
	// private Priority convertIntToPrio(Object value) {
	// int number = (Integer) value;
	// return priorityMap.get(number);
	// }
}
