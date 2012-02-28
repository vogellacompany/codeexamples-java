package com.example.rcp.demo.views;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.example.rcp.demo.model.ModelService;
import com.example.rcp.demo.model.Person;

public class PlaygroundView extends ViewPart {
	private DataBindingContext m_bindingContext;

	public PlaygroundView() {
	}

	public static String ID = "com.example.rcp.demo.views.playground";
	private List<Person> persons;
	private Person p;
	private Text text1;

	@Override
	public void createPartControl(final Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.marginRight = 20;
		parent.setLayout(gl_parent);
		persons = ModelService.getInstance();
		p = persons.get(0);
		new 
		
		text1 = new Text(parent, SWT.BORDER);
		text1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		text1.setText("Na mal sehen");
		m_bindingContext = initDataBindings();

	}

	@Override
	public void setFocus() {

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue firstNamePObserveValue = PojoProperties.value(
				"firstName").observe(p);
		IObservableValue observeTextText1ObserveWidget = WidgetProperties.text(
				SWT.Modify).observe(text1);
		bindingContext.bindValue(firstNamePObserveValue,
				observeTextText1ObserveWidget, null, null);
		//
		return bindingContext;
	}
}
