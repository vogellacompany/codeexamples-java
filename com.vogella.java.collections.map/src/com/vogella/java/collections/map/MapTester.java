package com.vogella.java.collections.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTester {
	public static void main(String[] args) {
		// Keys are Strings
		// Objects are also Strings
		
		Map<String, String> mMap = new HashMap<String, String>();
		mMap.put("Android", "Mobile");
		mMap.put("Eclipse", "IDE");
		mMap.put("Git", "Version control system");
		
		// Output 
		for (String key : mMap.keySet()) {
			System.out.println(key +" "+ mMap.get(key));
		}

		System.out.println("Changing the data");
		// Adding to the map
		mMap.put("iPhone", "Created by Apple");

		// Delete from map
		
		mMap.remove("Android");
		
		System.out.println("New output:");
		// Output
		for (String key : mMap.keySet()) {
			System.out.println(key +" "+ mMap.get(key));
		}
	}
}
