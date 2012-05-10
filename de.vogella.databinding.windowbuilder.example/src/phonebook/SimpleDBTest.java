package phonebook;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import phonebook.model.Person;

public class SimpleDBTest {

	private Button button;
	private ComboViewer comboViewer;
	private List m_persons;
	private Text text;
	protected Shell shell;

	/**
	* Launch the application
	* 
	* @param args
	*/
	public static void main(String[] args) {
		Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					SimpleDBTest window = new SimpleDBTest();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	* Open the window
	*/
	public void open() {
		final Display display = Display.getDefault();
		createData();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	private void createData() {
		m_persons = new ArrayList();
		m_persons.add(new Person("Joe", "", "", "", ""));
		m_persons.add(new Person("Jim", "", "", "", ""));
		m_persons.add(new Person("Joan", "", "", "", ""));
		m_persons.add(new Person("Steve", "", "", "", ""));
	}

	/**
	* Create contents of the window
	*/
	protected void createContents() {
		shell = new Shell();
		shell.setLayout(new GridLayout());
		shell.setSize(403, 127);
		shell.setText("SWT Application");

		comboViewer = new ComboViewer(shell, SWT.BORDER);
		comboViewer.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, false));

		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		button = new Button(shell, SWT.NONE);
		button.setEnabled(false);
		button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		button.setText("button");
		initDataBindings();
		//
	}
	protected DataBindingContext initDataBindings() {
		IObservableValue comboViewerSelectionObserveSelection = ViewersObservables.observeSingleSelection(comboViewer);
		IObservableValue textTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		IObservableValue buttonEnabledObserveWidget = SWTObservables.observeEnabled(button);
		IObservableValue comboViewerSelectionObserveSelection_1 = ViewersObservables.observeSingleSelection(comboViewer);
		IObservableValue comboViewerNameObserveDetailValue = BeansObservables.observeDetailValue(Realm.getDefault(), comboViewerSelectionObserveSelection, "name", java.lang.String.class);
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(buttonEnabledObserveWidget, comboViewerSelectionObserveSelection_1, null, new phonebook.ListSelectionUpdateValueStrategy());
		bindingContext.bindValue(textTextObserveWidget, comboViewerNameObserveDetailValue, null, null);
		//
		ObservableListContentProvider comboViewerContentProviderList = new ObservableListContentProvider();
		comboViewer.setContentProvider(comboViewerContentProviderList);
		//
		IObservableMap[] comboViewerLabelProviderMaps = BeansObservables.observeMaps(comboViewerContentProviderList.getKnownElements(), Person.class, new String[]{"name"});
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(comboViewerLabelProviderMaps));
		//
		WritableList m_personsWritableList = new WritableList(m_persons, Person.class);
		comboViewer.setInput(m_personsWritableList);
		//
		return bindingContext;
	}
}