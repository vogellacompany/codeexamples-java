package de.vogella.rcp.intro.dialogs.custom.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MyDialog extends TitleAreaDialog {

	private Text firstNameText;
	private Text lastNameText;
	private String firstName;
	private String lastName;

	public MyDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		// Set the title
		setTitle("This is my first custom dialog");
		// Set the message
		setMessage("This is a TitleAreaDialog", IMessageProvider.INFORMATION);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		// layout.horizontalAlignment = GridData.FILL;
		parent.setLayout(layout);

		// The text fields will grow with the size of the dialog
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;

		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("First Name");

		firstNameText = new Text(parent, SWT.BORDER);
		firstNameText.setLayoutData(gridData);
		
		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("Last Name");
		// You should not re-use GridData
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		lastNameText = new Text(parent, SWT.BORDER);
		lastNameText.setLayoutData(gridData);
		return parent;
	}

	private boolean isValidInput() {
		boolean valid = true;
		if (firstNameText.getText().length() == 0) {
			setErrorMessage("Please enter the first name");
			valid = false;
		}
		if (lastNameText.getText().length() == 0) {
			setErrorMessage("Please enter the last name");
			valid = false;
		}
		return valid;
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}

	// We need to have the textFields into Strings because the UI gets disposed
	// and the Text Fields are not accessible any more.
	private void saveInput() {
		firstName = firstNameText.getText();
		lastName = lastNameText.getText();

	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
