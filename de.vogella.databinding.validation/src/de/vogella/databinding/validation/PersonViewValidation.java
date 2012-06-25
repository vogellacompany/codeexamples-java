package de.vogella.databinding.validation;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.vogella.databinding.validation.model.PersonPojo;
import de.vogella.databinding.validation.validators.StringLongerThenTwo;

public class PersonViewValidation extends ViewPart {
	private static final String MESSAGE = "Name must be longer two letters";
	public static final String ID = "de.vogella.databinding.validation.personview";
	private PersonPojo person;
	private Text firstName;

	private Label errorLabel;
	private IObservableValue uiElement;
	private ControlDecoration firstNameDecorator;
	private ControlDecoration lastNameDecorator;
	private Text lastName;

	public PersonViewValidation() {
	}

	@Override
	public void createPartControl(Composite parent) {
		person = new PersonPojo();
		person.setFirstName("Lars");
		person.setLastName("Vogel");
		person.setGender("m");
		person.setAge(12);
		// Lets put thing to order
		Layout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		// Firstname
		Label nameLabel = new Label(parent, SWT.None);
		nameLabel.setText("Firstname: ");
		firstName = new Text(parent, SWT.BORDER);
		// Create the decorator which make the error message look really nice
		firstNameDecorator = createDecorator(firstName, MESSAGE);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		firstName.setLayoutData(gridData);

		// Lastname
		nameLabel = new Label(parent, SWT.None);
		nameLabel.setText("Lastname: ");
		lastName = new Text(parent, SWT.BORDER);
		// Create the decorator which make the error message look really nice
		lastNameDecorator = createDecorator(lastName, MESSAGE);
		lastName.setLayoutData(gridData);

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Write model");
		button1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(person.getFirstName());
				System.out.println(person.getLastName());
			}
		});
		// Button should take 2 rows
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		button1.setLayoutData(gridData);

		// This label will display all errors of all bindings
		Label descAllLabel = new Label(parent, SWT.NONE);
		descAllLabel.setText("All Validation Problems:");
		errorLabel = new Label(parent, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		errorLabel.setLayoutData(gridData);

		bindValues();

	}

	@Override
	public void setFocus() {
	}

	private ControlDecoration createDecorator(Text text, String message) {
		ControlDecoration controlDecoration = new ControlDecoration(text,
				SWT.LEFT | SWT.TOP);
		controlDecoration.setDescriptionText(message);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		controlDecoration.setImage(fieldDecoration.getImage());
		return controlDecoration;
	}

	private void bindValues() {
		// The DataBindingContext object will manage the databindings
		DataBindingContext bindingContext = new DataBindingContext();
		// First we bind the text field to the model
		// Here we define the UpdateValueStrategy
		UpdateValueStrategy update = new UpdateValueStrategy();
		update.setAfterConvertValidator(new StringLongerThenTwo(MESSAGE,
				firstNameDecorator));
		// Bind fistName
		bindingContext
				.bindValue(SWTObservables.observeText(firstName, SWT.Modify),
						PojoObservables.observeValue(person, "firstName"),
						update, null);

		// Bind lastName
		bindingContext.bindValue(SWTObservables.observeText(lastName,
				SWT.Modify), PojoObservables.observeValue(person, "lastName"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new StringLongerThenTwo(
								MESSAGE, lastNameDecorator)), null);

		// We listen to all errors via this binding
		uiElement = SWTObservables.observeText(errorLabel);
		// This one listenes to all changes
		bindingContext.bindValue(uiElement, new AggregateValidationStatus(
				bindingContext.getBindings(),
				AggregateValidationStatus.MAX_SEVERITY), null, null);
		// Lets change the color of the field lastName
		uiElement.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {
				if (uiElement.getValue().equals("OK")) {
					lastName.setBackground(Display.getCurrent().getSystemColor(
							SWT.COLOR_WHITE));

				} else {
					lastName.setBackground(Display.getCurrent().getSystemColor(
							SWT.COLOR_RED));
				}
			}
		});
	}
}
