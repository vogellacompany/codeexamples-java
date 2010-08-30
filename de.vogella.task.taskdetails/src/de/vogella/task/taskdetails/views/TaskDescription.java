package de.vogella.task.taskdetails.views;

import java.util.Calendar;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.vogella.task.application.views.TaskOverview;
import de.vogella.task.model.ITask;
import de.vogella.task.model.Priority;

public class TaskDescription extends ViewPart implements ISelectionListener {

	private Text text;
	private ITask task;
	private DateTime dueDateUi;
	private Combo status;

	@Override
	public void createPartControl(Composite parent) {
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);

		// Layout
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		parent.setLayout(layout);

		// Label dateFromLabel = new Label(parent, SWT.NONE);
		// dateFromLabel.setText("Start");
		// dateFrom = new DateTime(parent, SWT.BORDER | SWT.DATE |
		// SWT.DROP_DOWN);
		Label dueDateLabel = new Label(parent, SWT.NONE);
		dueDateLabel.setText("Due Date");
		dueDateUi = new DateTime(parent, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN
				| SWT.MEDIUM);
		dueDateUi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (task != null) {
					Calendar calendar = task.getDueDate();
					calendar.set(dueDateUi.getYear(), dueDateUi.getMonth(),
							dueDateUi.getDay());
					updateView();
				}
			}

		});
		Label label = new Label(parent, SWT.NONE);
		label.setText("Priority");

		status = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		status.setItems(getStatusItems());
		status.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = status.getSelectionIndex();
				if (selectionIndex == Priority.LOW.getIndex()) {
					task.setPriority(Priority.LOW);
				} else if (selectionIndex == Priority.MEDIUM.getIndex()) {
					task.setPriority(Priority.MEDIUM);
				} else {
					task.setPriority(Priority.HIGH);
				}
				updateView();
			}
		});

		text = new Text(parent, SWT.BORDER);
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				task.setDescription(text.getText());
			}
		});

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 4;
		gridData.verticalSpan = 6;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		text.setLayoutData(gridData);
		setEnable(false);
	}

	private String[] getStatusItems() {
		Priority[] priority = Priority.values();
		String[] a = new String[priority.length];
		for (int i = 0; i < priority.length; i++) {
			a[i] = priority[i].toString();
		}
		return a;
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object firstElement = sel.getFirstElement();
			if (firstElement instanceof ITask) {
				if (!text.getEditable()) {
					setEnable(true);
				}
				task = (ITask) firstElement;
				text.setText(task.getDescription());
				Calendar dueDate = task.getDueDate();
				dueDateUi.setDate(dueDate.get(Calendar.YEAR), dueDate
						.get(Calendar.MONTH), dueDate.get(Calendar.MONTH));
				status.select(task.getPriority().getIndex());
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
	}

	private void updateView() {
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView(TaskOverview.ID);
		((TaskOverview) view).refresh();

	}

	private void setEnable(boolean value) {
		text.setEditable(value);
		dueDateUi.setEnabled(value);
		status.setEnabled(value);
	}
}
