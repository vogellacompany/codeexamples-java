package de.vogella.jsf.trinidad.first;

import java.util.ArrayList;
import java.util.List;

public class TableValues {
	private List<Person> persons;

	public TableValues() {
		// Imagine a very complex DB access here
		persons = new ArrayList<Person>();
		Person p = new Person("Lars", "Vogel");
		persons.add(p);
		p = new Person("Jim", "Knopf");
		persons.add(p);
		p = new Person("Tim", "Tester");
		persons.add(p);
		p = new Person("Tim2", "Tester");
		persons.add(p);
		p = new Person("Tim3", "Tester");
		persons.add(p);
		p = new Person("Tim4", "Tester");
		persons.add(p);
		p = new Person("Tim5", "Tester");
		persons.add(p);
	}

	public List<Person> getPersons() {
		return persons;
	}
}
