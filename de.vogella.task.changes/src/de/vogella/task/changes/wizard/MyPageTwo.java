package de.vogella.task.changes.wizard;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

import de.vogella.task.model.ITask;
import de.vogella.task.model.Priority;
import de.vogella.task.model.Status;

public class MyPageTwo extends WizardPage {

	private final ITask task;
	private Composite container;
	private Combo priorityCombo;
	private DateTime dueDateUi;
	private Combo statusCombo;

	public MyPageTwo(ITask task) {
		super("Task");
		this.task = task;
		setTitle("Creating Task  - Part 2");
		setDescription("Maintain the due date, and a priority.");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label = new Label(container, SWT.NONE);
		label.setText("Due Date");
		dueDateUi = new DateTime(container, SWT.BORDER | SWT.DATE
				| SWT.DROP_DOWN | SWT.MEDIUM);
		dueDateUi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateValue();
			}

		});
		label = new Label(container, SWT.NONE);
		label.setText("Priority");
		priorityCombo = new Combo(container, SWT.BORDER);
		priorityCombo.setItems(computePriorities());
		priorityCombo.select(1);
		priorityCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateValue();
			}

		});
		label = new Label(container, SWT.NONE);
		label.setText("Status");
		statusCombo = new Combo(container, SWT.BORDER);
		statusCombo.setItems(computeStatus());
		statusCombo.select(1);
		statusCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateValue();
			}

		});
		setControl(container);
	}

	private String[] computeStatus() {
		String[] a = new String[Status.values().length];
		int i = 0;
		for (Status status : Status.values()) {
			a[i] = status.toString();
			i++;
		}
		return a;
	}

	private String[] computePriorities() {
		String[] a = new String[Priority.values().length];
		int i = 0;
		for (Priority prio : Priority.values()) {
			a[i] = prio.toString();
			i++;
		}
		return a;
	}

	private void updateValue() {
		if (task != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			task.setStartDate(calendar);
			calendar = GregorianCalendar.getInstance();
			calendar.set(dueDateUi.getYear(), dueDateUi.getMonth(), dueDateUi
					.getDay());
			task.setDueDate(calendar);

			for (Priority prio : Priority.values()) {
				String valueOf = String.valueOf((priorityCombo
						.getSelectionIndex()));
				if (prio.toString().equals(valueOf)) {
					task.setPriority(prio);
				}
			}
			for (Status status : Status.values()) {
				String valueOf = String.valueOf((statusCombo
						.getSelectionIndex()));
				if (status.toString().equals(valueOf)) {
					task.setStatus(status);
				}
			}
		}
	}

	public boolean isPageComplete() {
		updateValue();
		return true;
	}
}
