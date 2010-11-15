package de.vogella.databinding.emf.swt;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.vogella.databinding.emf.swt.model.ModelFactory;
import de.vogella.databinding.emf.swt.model.ModelPackage;
import de.vogella.databinding.emf.swt.model.Person;
import de.vogella.databinding.emf.swt.model.Phone;

public class View extends ViewPart {
	public static final String ID = "de.vogella.databinding.emf.swt.view";

	@Override
	public void createPartControl(Composite parent) {

		// Lets put thing to order
		Layout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		// Initialize the model
		ModelPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		ModelFactory factory = ModelFactory.eINSTANCE;

		final Person person = factory.createPerson();
		person.setFirstName("Lars");
		person.setLastName("Vogel");
		person.setGender("m");
		Phone phone = factory.createPhone();
		phone.setNumber("0123456789");
		person.setPhone(phone);
		// New text element
		Text firstName = new Text(parent, SWT.NONE);
		DataBindingContext bindingContext = new DataBindingContext();
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		firstName.setLayoutData(gridData);
		Text phoneNumber = new Text(parent, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		phoneNumber.setLayoutData(gridData);

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(firstName),
				EMFProperties.value(ModelPackage.Literals.PERSON__FIRST_NAME)
						.observe(person));

		FeaturePath feature = FeaturePath.fromList(
				ModelPackage.Literals.PERSON__PHONE,
				ModelPackage.Literals.PHONE__NUMBER);
		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(phoneNumber),
				EMFProperties.value(feature).observe(person));
		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Write model");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(person.getFirstName());
				System.out.println(person.getPhone().getNumber());
			}
		});

		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("Change model");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				person.setFirstName("Lars2");
				String reversedNumber = new StringBuffer(person.getPhone()
						.getNumber()).reverse().toString();
				person.getPhone().setNumber(reversedNumber);
			}
		});
	}

	public void setFocus() {
	}
}