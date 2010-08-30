package test;

public class ArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Ich habe es satt jedesmal eine Personen Variable zu erzeugen
		// Also definiere ich mir eine Liste von maximal 4 Personen
		// Also ein Array

		Person personArray[];
		personArray = new Person[4];

		Person person;

		person = new Person("Tim", "Motter");

		Address address = new Address();
		address.setCity("Chicago");
		address.setCountry("US");
		address.setStreet("Somewhere");
		address.setNumber("14a");
		person.setAddress(address);
		personArray[0] = person;

		person = new Person("Jane", "Motter");

		person.setAddress(address);
		personArray[1] = person;

		person = new Person("Kyle", "Motter");
		person.setAge(3);
		personArray[2] = person;

		person = new Person();
		person.setAge(0);
		person.setFirstName("Rita");
		person.setLastName("Motter");
		personArray[3] = person;

		for (int i = 0; i < 4; i++) {
			System.out.println(personArray[i].getFirstName());
		}
		int j = 0;
		while (j < 4) {
			System.out.println("Der Wert ist gleich " + j);
			System.out.println(personArray[j].getFirstName());
			j = j + 1; // ist gleich mit j++
		}
		System.out.println("Zweiter Lauf");
		j = -1;
		while (j < 3) {
			j = j + 1; // ist gleich mit j++
			System.out.println("Der Wert ist gleich " + j);
			System.out.println(personArray[j].getFirstName());
		}

		j = 3;
		while (j >= 0) {
			System.out.println("Der Wert ist gleich " + j);
			System.out.println(personArray[j].getFirstName());
			j--; // ist gleich mit j = j -1
		}

	}
}
