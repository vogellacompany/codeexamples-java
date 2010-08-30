package de.vogella.regex.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringMatcherTest {
	private StringMatcher m;

	@Before
	public void setup(){
		m = new StringMatcher();
	}

	@Test
	public void testIsTrue() {
		assertTrue(m.isTrue("true"));
		assertFalse(m.isTrue("true2"));
		assertFalse(m.isTrue("True"));
	}

	@Test
	public void testIsTrueVersion2() {
		assertTrue(m.isTrueVersion2("true"));
		assertFalse(m.isTrueVersion2("true2"));
		assertTrue(m.isTrueVersion2("True"));;
	}

	@Test
	public void testIsTrueOrYes() {
		assertTrue(m.isTrueOrYes("true"));
		assertTrue(m.isTrueOrYes("yes"));
		assertTrue(m.isTrueOrYes("Yes"));
		assertFalse(m.isTrueOrYes("no"));
	}

	@Test
	public void testContainsTrue() {
		assertTrue(m.containsTrue("thetruewithin"));
	}

	@Test
	public void testIsThreeLetters() {
		assertTrue(m.isThreeLetters("abc"));
		assertFalse(m.isThreeLetters("abcd"));
	}
	
	@Test
	public void testisNoNumberAtBeginning() {
		assertTrue(m.isNoNumberAtBeginning("abc"));
		assertFalse(m.isNoNumberAtBeginning("1abcd"));
		assertTrue(m.isNoNumberAtBeginning("a1bcd"));
		assertTrue(m.isNoNumberAtBeginning("asdfdsf"));
	}
	
	@Test
	public void testisIntersection() {
		assertTrue(m.isIntersection("1"));
		assertFalse(m.isIntersection("abcksdfkdskfsdfdsf"));
		assertTrue(m.isIntersection("skdskfjsmcnxmvjwque484242"));
	}
	

	@Test
	public void testLessThenThreeHundret() {
		assertTrue(m.isLessThenThreeHundret("288"));
		assertFalse(m.isLessThenThreeHundret("3288"));
		assertFalse(m.isLessThenThreeHundret("328 8"));
		assertTrue(m.isLessThenThreeHundret("1"));
		assertTrue(m.isLessThenThreeHundret("99"));
		assertFalse(m.isLessThenThreeHundret("300"));
	}

}
