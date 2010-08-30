package de.vogella.task.changes.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.vogella.task.model.ITask;

public class MyPageOne extends WizardPage {

	private final ITask task;
	private Composite container;

	public MyPageOne(ITask task) {
		super("Task");
		this.task = task;
		setTitle("Creating Task  - Part 1");
		setDescription("Maintain the summary and optional the description of the task.");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Summary");
		final Text summaryText = new Text(container, SWT.BORDER | SWT.SINGLE);
		summaryText.setText("");
		summaryText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!summaryText.getText().isEmpty()) {
					setPageComplete(true);
					task.setSummary(summaryText.getText());

				}
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		summaryText.setLayoutData(gd);

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Tasks Description");
		final Text descriptionText = new Text(container, SWT.BORDER | SWT.MULTI);
		descriptionText.setText("");
		descriptionText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!summaryText.getText().isEmpty()) {
					task.setDescription(descriptionText.getText());

				}
			}

		});
		gd = new GridData();
		gd.verticalAlignment = GridData.FILL;
		// gd.verticalSpan = 1;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		descriptionText.setLayoutData(gd);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
	}

}
