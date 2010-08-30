package de.vogella.geocoding.yahoo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import de.vogella.geocoding.germany.CallableGeocoder;
import de.vogella.geocoding.model.Address;
import de.vogella.geocoding.model.Coordinates;
import de.vogella.geocoding.yahoo.Geocoder;

public class GeocodeGermanyParallel {
	List<Coordinates> geocoordinates = new ArrayList<Coordinates>();
	private static final int NTHREDS = 2;

	@Test
	public void doSomething() {
		List<Address> addresses = readLocations();
		Collections.shuffle(addresses);
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		Map<Address, Future<Coordinates>> map = new HashMap<Address, Future<Coordinates>>();

		Geocoder geocoder = new Geocoder();
		// I'm getting the application ID as a parameter
		geocoder
				.setApplicationId("nw6Ay_nV34Fvou19P_IxiOOsyjmw8F8vpUsT9BsyVcjJTDZYcA4uSblX7UnsGWzVSvZmjHbMsws");
		int counter = 0;
		long startTime = System.currentTimeMillis();
		for (Address address : addresses) {
			CallableGeocoder worker = new CallableGeocoder(address);
			Future<Coordinates> submit = executor.submit(worker);
			map.put(address, submit);
			if (counter++ == 100) {
				break;
			}
		}
		for (Address address : map.keySet()) {
			try {
				Future<Coordinates> future = map.get(address);
				System.out.println(address + " " + future.get().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
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
				myAddress.setCity(plz_ort[1]);
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
}
