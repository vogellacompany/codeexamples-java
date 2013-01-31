package de.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MyParameterizedClassTest {

	private int multiplier;

	public MyParameterizedClassTest(int number) {
		this.multiplier = number;
	}

	// Creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1 }, { 5 }, { 121 } };
		return Arrays.asList(data);
	}

	@Test
	public void testMultiplyExeception() {
		MyClass tester = new MyClass();
		assertEquals("Result", multiplier * multiplier,
				tester.multiply(multiplier, multiplier));
	}

}
