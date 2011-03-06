package de.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyClassTest {

	@Test
	public void testMultiply() {
		MyClass tester = new MyClass();
		assertEquals("Result", 50, tester.multiply(10, 5));
	}

}
