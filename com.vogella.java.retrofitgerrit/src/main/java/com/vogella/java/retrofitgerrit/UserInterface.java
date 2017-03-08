package com.vogella.java.retrofitgerrit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UserInterface {

	private Button allChangesButton;
	private Button changesForUserIdButton;
	private Button changesForProjectNameButton;
	private Button upvoteButton;

	private List changes;

	private Text userIdText;
	private Text projectNameText;
	private Text upvoteText;

	private Display display;
	private Controller controller;

	public UserInterface() {
	}

	public void setController(Controller ctrl) {
		this.controller = ctrl;
	}

	public void initUi() {
		GridData gridData;
		display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(400, 300);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);

		allChangesButton = new Button(shell, SWT.PUSH);
		allChangesButton.setText("Get all changes");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 3;
		allChangesButton.setLayoutData(gridData);
		allChangesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.loadAllChanges();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		changes = new List(shell, SWT.NONE);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalSpan = 4;
		changes.setLayoutData(gridData);
		changes.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.getChangeInformation(changes.getSelectionIndex());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				super.widgetDefaultSelected(e);
			}

		});

		userIdText = new Text(shell, SWT.NONE);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		userIdText.setLayoutData(gridData);

		changesForUserIdButton = new Button(shell, SWT.PUSH);
		changesForUserIdButton.setText("Get changes for user id");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		changesForUserIdButton.setLayoutData(gridData);
		changesForUserIdButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String userId = userIdText.getText();
				if (userId != null && !userId.isEmpty()) {
					controller.loadChangesForUser(Integer.parseInt(userId));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		projectNameText = new Text(shell, SWT.NONE);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		projectNameText.setLayoutData(gridData);

		changesForProjectNameButton = new Button(shell, SWT.PUSH);
		changesForProjectNameButton.setText("Get changes for project");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		changesForProjectNameButton.setLayoutData(gridData);
		changesForProjectNameButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String projectName = projectNameText.getText();
				if (projectName != null && !projectName.isEmpty()) {
					controller.loadChangesForProject(projectName);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		upvoteText = new Text(shell, SWT.NONE);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		upvoteText.setLayoutData(gridData);

		upvoteButton = new Button(shell, SWT.PUSH);
		upvoteButton.setText("Upvote");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		upvoteButton.setLayoutData(gridData);
		upvoteButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.upvote(changes.getSelectionIndex());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public void updateList(java.util.List<Change> changes) {
		display.asyncExec(() -> {
			this.changes.removeAll();
			changes.forEach(change -> this.changes.add(change.getSubject()));
		});
	}

	public void updateTextFields(Change change) {
		display.asyncExec(() -> {
			projectNameText.setText(change.getProject());
			upvoteText.setText(change.getCurrentRevision());
			userIdText.setText(change.getOwner().getAccountId());
		});
	}
}
