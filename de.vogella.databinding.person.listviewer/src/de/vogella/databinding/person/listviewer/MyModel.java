package de.vogella.databinding.person.listviewer;

import java.util.ArrayList;
import java.util.List;

import de.vogella.databinding.person.model.Person;

public class MyModel {
	public static List<Person> createExampleData() {
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
