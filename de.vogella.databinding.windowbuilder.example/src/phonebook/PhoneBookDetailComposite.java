package phonebook;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import phonebook.model.Person;

public class PhoneBookDetailComposite extends Composite {

	private Person m_person = new Person();
	
	private Text m_mobile2Text;
	private Text m_mobile1Text;
	private Text m_phoneText;
	private Text m_emailText;
	private Text m_nameText;
	
	private DataBindingContext m_bindingContext;
	
	public PhoneBookDetailComposite(Composite parent, int style) {
		super(parent, style);

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		final Label descriptionLabel = new Label(this, SWT.NONE);
		descriptionLabel.setText("Description:");
		new Label(this, SWT.NONE);

		final Label label = new Label(this, SWT.NONE);
		label.setText("Name:");

		m_nameText = new Text(this, SWT.BORDER);
		final GridData gd_m_nameText = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		m_nameText.setLayoutData(gd_m_nameText);

		final Label emailLabel = new Label(this, SWT.NONE);
		emailLabel.setText("E-mail:");

		m_emailText = new Text(this, SWT.BORDER);
		final GridData gd_m_emailText = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		m_emailText.setLayoutData(gd_m_emailText);

		final Label phoneLabel = new Label(this, SWT.NONE);
		phoneLabel.setText("Phone:");

		m_phoneText = new Text(this, SWT.BORDER);
		final GridData gd_m_phoneText = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		m_phoneText.setLayoutData(gd_m_phoneText);

		final Label mobilePhone1Label = new Label(this, SWT.NONE);
		mobilePhone1Label.setText("Mobile Phone 1:");

		m_mobile1Text = new Text(this, SWT.BORDER);
		final GridData gd_m_mobile1Text = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		m_mobile1Text.setLayoutData(gd_m_mobile1Text);

		final Label mobilePhone2Label = new Label(this, SWT.NONE);
		mobilePhone2Label.setText("Mobile Phone 2:");

		m_mobile2Text = new Text(this, SWT.BORDER);
		final GridData gd_m_mobile2Text = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		m_mobile2Text.setLayoutData(gd_m_mobile2Text);
		
		m_bindingContext = initDataBindings();
	}
	protected DataBindingContext initDataBindings() {
		IObservableValue m_emailTextTextObserveWidget = SWTObservables.observeText(m_emailText, SWT.Modify);
		IObservableValue personPhoneObserveValue = BeansObservables.observeValue(m_person, "phone");
		IObservableValue m_phoneTextTextObserveWidget = SWTObservables.observeText(m_phoneText, SWT.Modify);
		IObservableValue m_nameTextTextObserveWidget = SWTObservables.observeText(m_nameText, SWT.Modify);
		IObservableValue personEmailObserveValue = BeansObservables.observeValue(m_person, "email");
		IObservableValue personNameObserveValue = BeansObservables.observeValue(m_person, "name");
		IObservableValue m_mobile2TextTextObserveWidget = SWTObservables.observeText(m_mobile2Text, SWT.Modify);
		IObservableValue personMobilePhone2ObserveValue = BeansObservables.observeValue(m_person, "mobilePhone2");
		IObservableValue personMobilePhone1ObserveValue = BeansObservables.observeValue(m_person, "mobilePhone1");
		IObservableValue m_mobile1TextTextObserveWidget = SWTObservables.observeText(m_mobile1Text, SWT.Modify);
		//
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(m_nameTextTextObserveWidget, personNameObserveValue, null, null);
		bindingContext.bindValue(m_emailTextTextObserveWidget, personEmailObserveValue, null, null);
		bindingContext.bindValue(m_phoneTextTextObserveWidget, personPhoneObserveValue, null, null);
		bindingContext.bindValue(m_mobile1TextTextObserveWidget, personMobilePhone1ObserveValue, null, null);
		bindingContext.bindValue(m_mobile2TextTextObserveWidget, personMobilePhone2ObserveValue, null, null);
		//
		return bindingContext;
	}
	public Person getPerson() {
		return m_person;
	}
	public void setPerson(Person person) {
		if (m_bindingContext != null) {
			m_bindingContext.dispose();
		}
		this.m_person = person;
		m_bindingContext = initDataBindings();
	}

}
