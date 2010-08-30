package de.vogella.junit.first;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

	@Test
	public void testMultiply() {
		MyClass tester = new MyClass();
		assertEquals("Result", 50, tester.multiply(10, 5));
	}

}
