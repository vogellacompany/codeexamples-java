package de.vogella.databinding.person.listviewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
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

		LabelProvider personLabelProvider = new LabelProvider() {
			private PropertyChangeListener pcl = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent e) {
					callChange(e.getSource());
				}
			};

			private void callChange(Object object) {
				fireLabelProviderChanged(new LabelProviderChangedEvent(this,
						object));
			}

			public String getText(Object element) {
				Person p = (Person) element;
				p.addPropertyChangeListener("firstName", pcl);
				p.addPropertyChangeListener("lastNamel", pcl);
				return p.getFirstName() + " " + p.getLastName();
			}
		};

		viewer.setLabelProvider(personLabelProvider);

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

		Button change = new Button(parent, SWT.PUSH);
		change.setText("change");
		change.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person p = (Person) input.get(0);
				p.setFirstName(p.getFirstName() + "1");
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
