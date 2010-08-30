package test;

import java.util.ArrayList;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person = new Person("Jim", "Knopf");
		persons.add(person);
		person = new Person("Jane", "Knopf");
		persons.add(person);
		// Cool for each loop
		for (Person p : persons) {
			System.out.println(p.getFirstName());
		}
		// The other for loop
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i).getFirstName());
		}
	}
}
