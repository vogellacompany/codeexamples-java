package de.vogella.databinding.person.listviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
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
		// Just a little bit layout
		parent.setLayout(new GridLayout(1, false));
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;

		// Define the viewer
		viewer = new ListViewer(parent);

		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		// This is now the new part
		IObservableMap observeMap = BeansObservables.observeMap(contentProvider
				.getKnownElements(), Person.class, "firstName");
		observeMap.putAll(BeansObservables.observeMap(contentProvider
				.getKnownElements(), Person.class, "lastName"));

		ObservableMapLabelProvider labelProvider = new ObservableMapLabelProvider(
				observeMap);
		viewer.setLabelProvider(labelProvider);

		viewer.setContentProvider(contentProvider);
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
				if (!viewer.getSelection().isEmpty()) {
					IStructuredSelection selection = (IStructuredSelection) viewer
							.getSelection();
					Person p = (Person) selection.getFirstElement();
					String temp = p.getLastName();
					p.setLastName(p.getFirstName());
					p.setFirstName(temp);
				}
			}
		});
	}
}
