package com.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MySecondClassTest {

	@BeforeClass
	public static void testSetup() {
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown() {
		MyClass tester = new MyClass();
		tester.multiply(1, 5);
	}

	@Test
	public void testMultiply() {
		MyClass tester = new MyClass();
		assertEquals("Result", 50, tester.multiply(10, 5));
	}
}
