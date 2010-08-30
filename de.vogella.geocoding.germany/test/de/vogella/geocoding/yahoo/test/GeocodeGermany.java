package de.vogella.geocoding.yahoo.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.vogella.geocoding.model.Address;
import de.vogella.geocoding.model.Coordinates;
import de.vogella.geocoding.yahoo.Geocoder;

public class GeocodeGermany {

	@Test
	public void doSomething() {
		List<Address> addresses = readLocations();
		Map<Address, Coordinates> map = new HashMap<Address, Coordinates>();
		// Collections.shuffle(addresses);
		System.out.println();
		assertEquals(addresses.size(), 31436);
		Geocoder geocoder = new Geocoder();
		// I'm getting the application ID as a parameter
		geocoder
				.setApplicationId("nw6Ay_nV34Fvou19P_IxiOOsyjmw8F8vpUsT9BsyVcjJTDZYcA4uSblX7UnsGWzVSvZmjHbMsws");
		int counter = 0;
		long startTime = System.currentTimeMillis();
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "8080");

		int start = 0;
		int end = 1000;
		for (int i = start; i <= end || i <= addresses.size(); i++) {
			Address address = addresses.get(i);
			Coordinates coordinates = geocoder.geocode(address);
			System.out.println(address + " " + coordinates);
			map.put(address, coordinates);
			if (counter++ == 100) {
				counter = 0;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		FileWriter output;
		try {
			output = new FileWriter("plzwithgeocoding.csv", true);
			BufferedWriter writer = new BufferedWriter(output);
			for (Address address : map.keySet()) {
				Coordinates coordinates = map.get(address);
				String postalCode = address.getPostalCode();
				if (postalCode.length() == 4) {
					postalCode = "0" + postalCode;
				}
				String s = postalCode + ";" + address.getCity() + ";"
						+ coordinates.getLatitude() + ";"
						+ coordinates.getLongitude();
				System.out.println(s);
				writer.write(s);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("This operation took " + elapsedTime / 1000
				+ " secs.");

	}

	// Create Location List
	private List<Address> readLocations() {
		List<Address> list = new ArrayList<Address>();
		BufferedReader br = null;
		try {
			File file = new File("plz.csv");
			FileReader reader = new FileReader(file);
			br = new BufferedReader(reader);
			ArrayList<String> fileLines = new ArrayList<String>();
			// Add postal code / city to String ArrayList
			String s1;
			while ((s1 = br.readLine()) != null) {
				fileLines.add(s1);
			}
			for (String string : fileLines) {
				String[] plz_ort = string.split(";");
				Address myAddress = new Address();
				myAddress.setPostalCode(plz_ort[0]);
				myAddress.setCity(convertUmlauete(plz_ort[1]));
				myAddress.setCountry("DE");
				list.add(myAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private String convertUmlauete(String string) {
		String s = string;
		s = string.replaceAll("ä", "ae");
		s = string.replaceAll("ü", "ue");
		s = string.replaceAll("ö", "oe");
		s = string.replaceAll("Ä", "Ae");
		s = string.replaceAll("Ü", "Ue");
		s = string.replaceAll("Ö", "Oe");
		return s;
	}
}
