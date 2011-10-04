package de.vogella.databinding.viewer;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.vogella.databinding.viewer.model.MyModel;
import de.vogella.databinding.viewer.model.Person;

public class ViewTable extends View {
	private TableViewer viewer;
	private WritableList input;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;

		// Define the viewer
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.H_SCROLL
				| SWT.V_SCROLL);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = 3;
		viewer.getControl().setLayoutData(data);

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(100);
		column.getColumn().setText("First Name");
		column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(100);
		column.getColumn().setText("Last Name");
		column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(100);
		column.getColumn().setText("Married");
		viewer.getTable().setHeaderVisible(true);

		// Now lets bind the values
		// No extra label provider / content provider / setInput required
		input = new WritableList(MyModel.getPersons(), Person.class);
		ViewerSupport.bind(
				viewer,
				input,
				BeanProperties.values(new String[] { "firstName", "lastName",
						"married" }));

		// The following buttons are there to test the binding
		Button delete = new Button(parent, SWT.PUSH);
		delete.setText("Delete");
		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteFirstPerson();
			}
		});

		Button add = new Button(parent, SWT.PUSH);
		add.setText("Add");
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addPerson();
			}
		});
		Button change = new Button(parent, SWT.PUSH);
		change.setText("Switch First / Lastname");
		change.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switchFirstLastName();
			}
		});

		getViewSite().setSelectionProvider(viewer);
		// Listen to the selection of the viewer
		Label label = new Label(parent, SWT.NONE);
		label.setText("Initial");
		DataBindingContext ctx = new DataBindingContext();
		IObservableValue selection = ViewerProperties.singleSelection()
				.observe(viewer);
		IObservableValue target = WidgetProperties.text().observe(label);
		ctx.bindValue(target, selection);

	}

	public void deleteFirstPerson() {
		if (!viewer.getSelection().isEmpty()) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			Person p = (Person) selection.getFirstElement();
			input.remove(p);
		}
	}

	public void addPerson() {
		Person p = new Person();
		p.setFirstName("Test");
		p.setLastName("Test1");
		input.add(p);
	}

	public void changeFirstPersonName() {
		Person p = (Person) input.get(0);
		p.setFirstName(p.getFirstName() + "1");
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
