package de.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyClassTest {

	@BeforeClass
	public static void testSetup() {
		// Preparation of the unit tests
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultiplyExeception() {
		MyClass tester = new MyClass();
		tester.multiply(500, 500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultiplty() {
		MyClass tester = new MyClass();
		assertEquals("Result", 50, tester.multiply(1, 5));
	}

}
