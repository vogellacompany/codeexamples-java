package de.vogella.datastructures.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MyMapTest {


	@Test
	public void testStandardMap() {
		// Standard Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Lars", 1);
		map.put("Lars", 2);
		map.put("Lars", 1);
		assertEquals(map.get("Lars"), (Integer) 1);

		for (int i = 0; i < 100; i++) {
			map.put(String.valueOf(i), i);
		}
		assertEquals(map.size(), 101);

		assertEquals(map.get("51"), (Integer) 51);
		map.remove("Lars");
		assertNull(map.get("Lars"));
		assertEquals(map.get("1"), (Integer) 1);
		assertEquals(map.size(), 100);

		for (int i = 101; i < 20000; i++) {
			map.put(String.valueOf(i), i);
		}

		// Lets look at the runtime
		long startTime = System.currentTimeMillis();
		for (String s : map.keySet()) {
			map.get(s);
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

	}

	@Test
	public void testMapMap() {

		// MyMap
		MyMap<String, Integer> map = new MyMap<String, Integer>();
		map.put("Lars", 1);
		map.put("Lars", 2);
		map.put("Lars", 1);
		assertEquals(map.get("Lars"), (Integer)1);
		for (int i = 0; i < 100; i++) {
			map.put(String.valueOf(i), i);
		}
		assertEquals(map.size(), 101);
		assertEquals(map.get("51"), (Integer) 51);

		map.remove("Lars");
		assertNull(map.get("Lars"));
		assertEquals(map.get("1"), (Integer) 1);
		assertEquals(map.size(), 100);

		for (int i = 101; i < 20000; i++) {
			map.put(String.valueOf(i), i);
		}

		// Lets look at the runtime
		long startTime = System.currentTimeMillis();
		for (String s : map.keySet()) {
			map.get(s);
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
}
