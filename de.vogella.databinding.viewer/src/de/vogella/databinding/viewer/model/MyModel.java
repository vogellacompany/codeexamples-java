package de.vogella.databinding.viewer.model;

import java.util.ArrayList;
import java.util.List;

public class MyModel {
	public static List<Person> getPersons() {
		List<Person> persons = new ArrayList<Person>();
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
		return persons;
	}
}
