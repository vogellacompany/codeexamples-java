package de.vogella.databinding.person.listviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.vogella.databinding.person.model.Person;

public class View extends ViewPart {

	private ListViewer viewer;
	private WritableList input;

	@Override
	public void createPartControl(Composite parent) {
		// Just a little bit layout
		parent.setLayout(new GridLayout(1, false));

		// Define the viewer
		viewer = new ListViewer(parent);
		viewer.setContentProvider(new ObservableListContentProvider());
		List<Person> persons = new ArrayList<Person>();
		// Just for testing we create sample data
		createExampleData(persons);
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
				p.setFirstName("Test");
				p.setLastName("Test");
				input.add(p);
			}
		});
	}

	protected void createExampleData(List<Person> persons) {
		Person p = new Person();
		p.setFirstName("Joe");
		p.setLastName("Darcey");
		persons.add(p);
		p = new Person();
		p.setFirstName("Jim");
		p.setLastName("Knopf");
		persons.add(p);
		p = new Person();
		p.setFirstName("Jim");
		p.setLastName("Bean");
		persons.add(p);
	}

	@Override
	public void setFocus() {

	}

}
