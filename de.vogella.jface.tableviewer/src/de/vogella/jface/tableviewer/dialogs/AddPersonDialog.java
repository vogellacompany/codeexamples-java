package de.vogella.jface.tableviewer.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.vogella.jface.tableviewer.model.Person;

public class AddPersonDialog extends TitleAreaDialog {

	private Text text1;
	private Text text2;
	private Person person;
	private Button button1;
	private Combo combo1;

	public Person getPerson() {
		return person;
	}

	public AddPersonDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new Person");
		setMessage("Please enter the data of the new person",
				IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("First Name");
		text1 = new Text(parent, SWT.BORDER);
		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("Last Name");
		text2 = new Text(parent, SWT.BORDER);
		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("Gender");
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 2;
		combo1 = new Combo(parent, SWT.READ_ONLY);
		combo1.add("male");
		combo1.add("female");
		button1 = new Button(parent, SWT.CHECK);
		button1.setText("Is married?");
		button1.setLayoutData(gd);
		return parent;

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;

		Button button = new Button(parent, SWT.PUSH);
		button.setText("OK");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (text1.getText().length() != 0
						&& text2.getText().length() != 0
						&& combo1.getItem(combo1.getSelectionIndex()).length() != 0) {
					person = new Person(text1.getText(), text2.getText(),
							combo1.getItem(combo1.getSelectionIndex()), button1
									.getSelection());
					close();

				} else {
					setErrorMessage("Please enter all data");
				}
			}
		});
	}
}
