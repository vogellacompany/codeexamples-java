package de.vogella.jface.tableviewer.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
	INSTANCE;

	private List<Person> persons;

	private ModelProvider() {
		persons = new ArrayList<Person>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		persons.add(new Person("Rainer", "Zufall", "male", true));
		persons.add(new Person("Reiner", "Babbel", "male", true));
		persons.add(new Person("Marie", "Dortmund", "female", false));
		persons.add(new Person("Holger", "Adams", "male", true));
		persons.add(new Person("Juliane", "Adams", "female", true));
	}

	public List<Person> getPersons() {
		return persons;
	}

}
