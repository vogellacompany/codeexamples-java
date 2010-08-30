package de.vogella.task.application.views.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import de.vogella.task.model.ITask;

public class TaskSorter extends ViewerSorter {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public TaskSorter() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		ITask p1 = (ITask) e1;
		ITask p2 = (ITask) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = p1.getDueDate().compareTo(p2.getDueDate());
			break;
		case 1:
			rc = p1.getDueDate().compareTo(p2.getDueDate());
			break;
		case 2:
			rc = p1.getSummary().compareTo(p2.getSummary());
			break;
		case 3:
			rc = p1.getStatus().compareTo(p2.getStatus());
			break;
		// break;
		// case 2:
		// rc = p1.getGender().compareTo(p2.getGender());
		// break;
		// case 3:
		// if (p1.isMarried() == p2.isMarried()) {
		// rc = 1;
		// } else
		// rc = -1;
		// break;
		default:
			rc = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}
}
