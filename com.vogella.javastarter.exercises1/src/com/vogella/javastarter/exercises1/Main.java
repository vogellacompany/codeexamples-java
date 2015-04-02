package com.vogella.javastarter.exercises1;

public class Main {
	public static void main(String[] args) {
		// I create a person
		Person pers = new Person("Jim", "Knopf", 31);
		// I set the age of the person to 32
		pers.setAge(32);

		//  this calls the toString method on the pers object
		System.out.println(pers);
		/*
		 * Actually System.out.println calls always toString, if you do not
		 * specify it so you could also have written System.out.println(pers);
		 */
		// I create an address
		Address address = new Address();
		// I set the values for the address
		address.setCity("Heidelberg");
		address.setCountry("Germany");
		address.setNumber("104");
		address.setPostalCode("69214");
		address.setStreet("Musterstr.");

		// I assign the address to the person
		pers.setAddress(address);

		// I do not need address any more
		address = null;

		// person is moving to the next house in the same street
		pers.getAddress().setNumber("105");

	}

}