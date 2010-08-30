package de.vogella.regex.eitheror;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EitherOrCheck {
	@Test
	public void testSimpleTrue() {
		String s = "humbapumpa jim";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa jom";
		assertFalse(s.matches(".*(jim|joe).*"));
		s = "humbaPumpa joe";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa joe jim";
		assertTrue(s.matches(".*(jim|joe).*"));
	}
}
