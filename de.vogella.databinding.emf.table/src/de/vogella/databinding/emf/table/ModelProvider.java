package de.vogella.databinding.emf.table;

import org.eclipse.ui.internal.Model;

import de.vogella.databinding.emf.table.model.ModelFactory;
import de.vogella.databinding.emf.table.model.Todo;

public class ModelProvider {

	public Model createModel() {

		String[] summeries = new String[] { "Read Java", "Hack more" };
		String[] descriptions = new String[] { "Effective Java", "Hack, hack, hack" };
		int[] personAge = new int[] { 23, 34, 12, 45 };
		String[] cities = new String[] { "Rostock", "Bremen", "Hannover", "Dortmund" };

		ModelFactory factory = ModelFactory.eINSTANCE;
		Model model = factory.createModel();
		Todo todo;

		for (int i = 0; i < summeries.length; i++) {
			person = factory.createTodo();
			person.setFirstName(summeries[i]);
			person.setLastName(descriptions[i]);
			person.setAge(personAge[i]);

			city = factory.createCity();
			city.setName(cities[i]);

			person.setLivesIn(city);
			model.getPersons().add(person);
		}

		return model;
	}
}
