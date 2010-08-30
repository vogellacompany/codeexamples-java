package de.vogella.databinding.personpojo;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.vogella.databinding.personpojo.model.PersonPojo;

public class MyView extends ViewPart {
	public static final String ID = "de.vogella.databinding.personpojo.myview";

	private PersonPojo person;
	private Text firstName;
	private Button married;

	public MyView() {
		person = new PersonPojo();
		person.setFirstName("Joe");
		person.setLastName("Jim");
	}

	@Override
	public void createPartControl(Composite parent) {
		Layout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label label = new Label(parent, SWT.NONE);
		label.setText("FirstName");
		firstName = new Text(parent, SWT.BORDER);
		Label marriedLabel = new Label(parent, SWT.NONE);
		marriedLabel.setText("Maried");
		married = new Button(parent, SWT.CHECK);
		bindValues();

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Write model");
		button1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(person.getFirstName());
				System.out.println(person.isMarried());
			}
		});

	}

	private void bindValues() {
		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(firstName,
				SWT.Modify), PojoObservables.observeValue(person, "firstName"),
				null, null);

		bindingContext.bindValue(SWTObservables.observeSelection(married),
				PojoObservables.observeValue(person, "married"), null, null);
	}

	@Override
	public void setFocus() {

	}

}
