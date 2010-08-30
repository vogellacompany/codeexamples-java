package test;

import math.MyMath;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyMathTest {
	@Test
	public void testMulti() {
		MyMath math = new MyMath();
		assertEquals(50, math.multi(5, 10));
	}
}
