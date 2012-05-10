package aaa.hamburg;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	private DataBindingContext m_bindingContext;

	public View() {
	}

	public static final String ID = "aaa.hamburg.view";
	private List<Person> list;
	private Table table;
	private ListViewer listViewer;
	private Text text;
	private TableViewer tableViewer;

	public void createPartControl(Composite parent) {
		list = new ArrayList<Person>();
		list.add(createPerson("Lars"));
		list.add(createPerson("Marcel"));
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(parent, SWT.BORDER | SWT.SMOOTH);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		listViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List list_1 = listViewer.getList();

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));

		tableViewer = new TableViewer(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);

		text = new Text(composite_2, SWT.BORDER);
		text.setSize(75, 27);
		sashForm_1.setWeights(new int[] { 1, 1 });
		sashForm.setWeights(new int[] { 1, 1 });

		m_bindingContext = initDataBindings();
	}

	private Person createPerson(String name) {
		Person p = new Person();
		p.setFirstName(name);
		p.setLastName("Lars" + name);
		p.setMarried(false);
		return p;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		listViewer.getControl().setFocus();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableList selfList = Properties.selfList(Person.class).observe(
				list);
		ViewerSupport.bind(listViewer, selfList, PojoProperties.values(
				Person.class, new String[] { "new String[] { "f" }));
		//
		return bindingContext;
	}
}