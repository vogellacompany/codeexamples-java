package de.vogella.databinding.person.listviewer;

import java.util.List;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.vogella.databinding.person.model.Person;

public class ViewElements extends View {
	private ListViewer viewer;
	private WritableList input;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;

		// Define the viewer
		viewer = new ListViewer(parent);
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		viewer.setContentProvider(contentProvider);

		// Create sample data
		List<Person> persons = createExampleData();
		input = new WritableList(persons, Person.class);
		// Set the writeableList as input for the viewer
		viewer.setInput(input);

		Button delete = new Button(parent, SWT.PUSH);
		delete.setText("Delete");
		delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!viewer.getSelection().isEmpty()) {
					IStructuredSelection selection = (IStructuredSelection) viewer
							.getSelection();
					Person p = (Person) selection.getFirstElement();
					input.remove(p);
				}
			}
		});

		Button add = new Button(parent, SWT.PUSH);
		add.setText("Add");
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person p = new Person();
				p.setFirstName("Test1");
				p.setLastName("Test2");
				input.add(p);
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
	}

	public void switchFirstLastName() {
		if (!viewer.getSelection().isEmpty()) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			Person p = (Person) selection.getFirstElement();
			String temp = p.getLastName();
			p.setLastName(p.getFirstName());
			p.setFirstName(temp);
		}
	}
}
