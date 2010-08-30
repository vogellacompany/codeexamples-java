package de.vogella.task.application.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.vogella.task.application.views.editing.TaskEditingSupport;
import de.vogella.task.application.views.provider.TaskContentProvider;
import de.vogella.task.application.views.provider.TaskLabelProvider;
import de.vogella.task.application.views.sorter.TaskSorter;
import de.vogella.task.dao.MockDao;

public class TaskOverview extends ViewPart {
	public static final String ID = "de.vogella.task.application.views.TaskOverview";

	private TableViewer viewer;

	private TaskSorter taskSorter;

	@Override
	public void createPartControl(Composite parent) {
		createViewer(parent);
		getSite().setSelectionProvider(viewer);

	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		createColumns(viewer);
		cellEditors(viewer);
		viewer.setContentProvider(new TaskContentProvider());
		viewer.setLabelProvider(new TaskLabelProvider());
		viewer.setInput(MockDao.INSTANCE.getTasks());
		taskSorter = new TaskSorter();
		viewer.setSorter(taskSorter);
	}

	private void cellEditors(TableViewer viewer2) {
	}

	// This will create the columns for the table
	private void createColumns(final TableViewer viewer) {
		String[] titles = { "", "Due Date", "Summary", "Priority", "Status" };
		int[] bounds = { 60, 140, 200, 100, 100 };

		for (int i = 0; i < titles.length; i++) {
			final int index = i;
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			final TableColumn tableColumn = column.getColumn();
			tableColumn.setText(titles[i]);
			tableColumn.setWidth(bounds[i]);
			tableColumn.setResizable(true);
			tableColumn.setMoveable(true);
			// Setting the right sorter
			tableColumn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					taskSorter.setColumn(index);
					int dir = viewer.getTable().getSortDirection();
					if (viewer.getTable().getSortColumn() == tableColumn) {
						dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
					} else {

						dir = SWT.DOWN;
					}
					viewer.getTable().setSortDirection(dir);
					viewer.getTable().setSortColumn(tableColumn);
					viewer.refresh();
				}
			});
			column.setEditingSupport(new TaskEditingSupport(i, viewer));
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void refresh() {
		viewer.setInput(MockDao.INSTANCE.getTasks());
	}

	public Viewer getViewer() {
		return viewer;
	}
}