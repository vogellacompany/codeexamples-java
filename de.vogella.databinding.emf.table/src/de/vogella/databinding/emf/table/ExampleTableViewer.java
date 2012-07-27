package de.vogella.databinding.emf.table;

import java.util.HashMap;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.vogella.databinding.emf.table.model.Model;
import de.vogella.databinding.emf.table.model.ModelPackage;

public class ExampleTableViewer extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {

		// create a table viewer with visible header
		TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION);
		viewer.getTable().setHeaderVisible(true);

		// create an example model which will be visualized
		Model model = createModel();

		// create a content provider
		ObservableListContentProvider cp = new ObservableListContentProvider();

		// put all attributes (from class Person) that are going to be shown
		// into a map
		// and associate the column title
		HashMap<EAttribute, String> attributeMap = new HashMap<EAttribute, String>();
		attributeMap.put(ModelPackage.Literals.TODO__SUMMARY, "Summary");
		attributeMap
				.put(ModelPackage.Literals.TODO__DESCRIPTION, "Description");

		// create a column for each attribute & setup the databinding
		for (EAttribute attribute : attributeMap.keySet()) {
			// create a new column
			TableViewerColumn tvc = new TableViewerColumn(viewer, SWT.LEFT);
			// determine the attribute that should be observed
			IObservableMap map = EMFProperties.value(attribute).observeDetail(
					cp.getKnownElements());
			tvc.setLabelProvider(new ObservableMapCellLabelProvider(map));
			// set the column title & set the size
			tvc.getColumn().setText(attributeMap.get(attribute));
			tvc.getColumn().setWidth(80);
		}

		// define extra attributes (to be visualized in the table viewer) from
		// external classes feature path is a concatenation of features starting
		// at the person class
		FeaturePath path = FeaturePath.fromList(
				ExamplePackage.Literals.PERSON__LIVES_IN,
				ExamplePackage.Literals.CITY__NAME);
		// bind the feature and setup a table column
		IObservableMap map = EMFProperties.value(path).observeDetail(
				cp.getKnownElements());
		TableViewerColumn tvc = new TableViewerColumn(viewer, SWT.LEFT);
		tvc.setLabelProvider(new ObservableMapCellLabelProvider(map));
		tvc.getColumn().setText("lives in");
		tvc.getColumn().setWidth(80);

		// set the content provider
		viewer.setContentProvider(cp);
		// set the model (which is a list of persons)
		viewer.setInput(EMFProperties.list(
				ExamplePackage.Literals.MODEL__PERSONS).observe(model));

	}

	private Model createModel() {

		String[] firstNames = new String[] { "Klaus", "Hans", "Herrmann",
				"Konrad" };
		String[] lastNames = new String[] { "L�ffler", "Meyer", "M�ller",
				"Schulze" };
		int[] personAge = new int[] { 23, 34, 12, 45 };
		String[] cities = new String[] { "Rostock", "Bremen", "Hannover",
				"Dortmund" };

		ExampleFactory factory = ExampleFactory.eINSTANCE;
		Model model = factory.createModel();
		Person person;
		City city;

		for (int i = 0; i < firstNames.length; i++) {
			person = factory.createPerson();
			person.setFirstName(firstNames[i]);
			person.setLastName(lastNames[i]);
			person.setAge(personAge[i]);

			city = factory.createCity();
			city.setName(cities[i]);

			person.setLivesIn(city);
			model.getPersons().add(person);
		}

		return model;
	}

	@Override
	public void setFocus() {
	}
}
