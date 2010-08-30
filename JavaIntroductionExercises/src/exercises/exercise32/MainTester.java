package exercises.exercise32;

import test.Person;

public class MainTester {

	/**
	 * @param args
	 */

	// Target: Create Persons from File!!!!!
	public static void main(String[] args) {
		MyFile file = new MyFile();
		Person person;
		String s = file.readTextFile("c:\\temp\\person.txt");
		String lines[] = s.split("\n");
		Person[] persons = new Person[lines.length];
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
			String names[] = lines[i].split(",");
			person = new Person(names[0], names[1]);
			persons[i] = person;
			System.out.println(names[0]);
			System.out.println(names[1]);
		}
		// Wie viele Personen habe ich jetzt?

		System.out.println(persons.length);
		person = persons[0];
		person.getFirstName();

		boolean haveSuchAPerson = false;
		// Do I have a person starting with the firstname starting with J and a
		// lastname with K.
		int i = 0;
		while (i < persons.length && !haveSuchAPerson) {
			boolean firstNameCheck = persons[i].getFirstName().toUpperCase()
					.startsWith("J");
			if (firstNameCheck) {
				haveSuchAPerson = persons[i].getLastName().toUpperCase()
						.startsWith("K");
			}
			i++;
		}

		if (haveSuchAPerson) {
			System.out
					.println("I have a person starting with a firstname starting with J and a lastname starting with K");
		} else {
			System.out.println("No such person");
		}

	}
}
