package de.vogella.databinding.example;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.vogella.databinding.example.model.Person;

public class ViewWritableValue extends View {
	private WritableValue value;

	@Override
	public void createPartControl(Composite parent) {
		value = new WritableValue();
		parent.setLayout(new GridLayout(3, false));
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		Text text = new Text(parent, SWT.BORDER);
		Button button = new Button(parent, SWT.PUSH);
		button.setText("New Person");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person p = new Person();
				p.setFirstName("Lars");
				value.setValue(p);
			}
		});

		button = new Button(parent, SWT.PUSH);
		button.setText("Another Person");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person p = new Person();
				p.setFirstName("Jack");
				value.setValue(p);
			}
		});
		DataBindingContext ctx = new DataBindingContext();
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(
				text);
		IObservableValue model = BeanProperties.value("firstName")
				.observeDetail(value);
		ctx.bindValue(target, model);
	}

	@Override
	public void setFocus() {
	}
}
