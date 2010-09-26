package de.vogella.rcp.intro.jfaceviewer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.jfaceviewer.view";

	private ComboViewer viewer;

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label label = new Label(parent, SWT.NONE);
		label.setText("Select a person:");
		viewer = new ComboViewer(parent, SWT.READ_ONLY);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Person) {
					Person person = (Person) element;
					return person.getFirstName();
				}
				return super.getText(element);
			}
		});
		Person[] persons = new Person[] { new Person("Lars", "Vogel"),
				new Person("Tim", "Taler"), new Person("Jim", "Knopf") };
		// Set set the input to the viewer this input will be send to the
		// content provider
		viewer.setInput(persons);
		// React to the selection of the viewer
		// Note that the viewer return the real object and not just a string
		// representation
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				System.out.println(((Person) selection.getFirstElement())
						.getLastName());
			}
		});

		// You can select a object directly via the domain object
		Person person = persons[0];
		viewer.setSelection(new StructuredSelection(person));
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}