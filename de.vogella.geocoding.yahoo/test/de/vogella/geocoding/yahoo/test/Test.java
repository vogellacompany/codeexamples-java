package de.vogella.geocoding.yahoo.test;

import de.vogella.geocoding.model.Address;
import de.vogella.geocoding.model.Coordinates;
import de.vogella.geocoding.yahoo.Geocoder;

public class Test {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("You need to parse the application ID as parameter or change this coding.");
			System.exit(0);
		}
		Geocoder geocoder = new Geocoder();
		// I'm getting the application ID as a parameter
		geocoder.setApplicationId(args[0]);
		Address address = new Address("69214", "Eppelheim", "DE");
		Coordinates coordinates = geocoder.geocode(address);
		System.out.println(coordinates);
		address = new Address("10042", "Berlin", "DE");
		coordinates = geocoder.geocode(address);
		System.out.println(coordinates);
		address = new Address("20095", "Hamburg", "DE");
		coordinates = geocoder.geocode(address);
		System.out.println(coordinates);
	}
}
