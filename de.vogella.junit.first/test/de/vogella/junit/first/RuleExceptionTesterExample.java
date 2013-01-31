package de.vogella.junit.first;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleExceptionTesterExample {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void throwsIllegalArgumentExceptionIfIconIsNull() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Negative value not allowed");
		ClassToBeTested t = new ClassToBeTested();
		t.methodToBeTest(-1);
	}
}
