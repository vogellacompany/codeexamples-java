package com.vogella.java.collections.map;

import java.util.HashMap;
import java.util.Map;

public class MapTester {
	public static void main(String[] args) {
		
		// keys are Strings
		// objects are also Strings
		Map<String, String> map = new HashMap<>();
		fillData(map);

		// write to command line
		map.forEach((k, v) -> System.out.printf("%s %s%n", k, v));

		// add and remove from the map
		map.put("iPhone", "Created by Apple");
		map.remove("Android");

		// write again to command line
		map.forEach((k, v) -> System.out.printf("%s %s%n", k, v));
	
	}

	private static void fillData(Map<String, String> map) {
		map.put("Android", "Mobile");
		map.put("Eclipse IDE", "Java");
		map.put("Eclipse RCP", "Java");
		map.put("Git", "Version control system");
		
	}

}
