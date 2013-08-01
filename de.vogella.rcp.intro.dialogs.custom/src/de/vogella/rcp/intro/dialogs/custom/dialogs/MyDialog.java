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
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MyDialog extends TitleAreaDialog {

	private Text txtFirstName;
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
		Composite composite = (Composite) super.createDialogArea(parent);
		Composite area = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		area.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		area.setLayout(layout);
		Label lbtFirstName = new Label(area, SWT.NONE);
		lbtFirstName.setText("First Name");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		txtFirstName = new Text(area, SWT.BORDER);
		txtFirstName.setLayoutData(dataFirstName);

		Label lbtLastName = new Label(area, SWT.NONE);
		lbtLastName.setText("Last Name");

		// You should not re-use GridData
		GridData dataLastName = new GridData();
		dataLastName.grabExcessHorizontalSpace = true;
		dataLastName.horizontalAlignment = GridData.FILL;
		lastNameText = new Text(area, SWT.BORDER);
		lastNameText.setLayoutData(dataLastName);
		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// We need to save the values of the Text fields into Strings because the UI
	// gets disposed
	// and the Text fields are not accessible any more.
	private void saveInput() {
		firstName = txtFirstName.getText();
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
