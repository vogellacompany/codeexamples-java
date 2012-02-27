package com.example.rcp.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ModelService {
	private static List<Person> service;

	private ModelService() {
		// Not initialize
	}

	public synchronized static List<Person> getInstance() {
		if (service == null) {
			service = createPerson();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return service;
	}

	private static List<Person> createPerson() {
		List<Person> list = new ArrayList<Person>();
		Person person = new Person("Jack", "Hacker");
		person.setMarried(true);
		list.add(person);
		person = new Person("Tim", "Motter");
		list.add(person);
		person = new Person("Björn", "Bernard");
		person.setMarried(true);
		list.add(person);
		person = new Person("Simone", "Hacker");
		list.add(person);
		return list;
	}
}
