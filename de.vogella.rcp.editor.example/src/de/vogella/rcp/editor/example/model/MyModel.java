package de.vogella.rcp.editor.example.model;

import java.util.ArrayList;
import java.util.List;

public class MyModel {

	private static MyModel model;
	private List<Person> persons = new ArrayList<Person>();

	private MyModel() {
		Person person = new Person("Hans", "Nase");
		persons.add(person);
		person = new Person("Jim", "Knopf");
		persons.add(person);
	}

	public static MyModel getInstance() {
		if (model == null) {
			model = new MyModel();
		}
		return model;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public Person getPersonById(int id) {
		for (Person person : persons) {
			if (person.getId() == id) {
				System.out.println("returned");
				return person;
			}
		}
		return null;
	}
}
