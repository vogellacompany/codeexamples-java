package de.vogella.regex.phonenumber;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CheckPhone {
	
	@Test
	public void testSimpleTrue() {
		String pattern = "\\d\\d\\d([,\\s])?\\d\\d\\d\\d";
		String s= "1233323322";
		assertFalse(s.matches(pattern));
		s = "1233323";
		assertTrue(s.matches(pattern));
		s = "123 3323";
		assertTrue(s.matches(pattern));
	}
}
