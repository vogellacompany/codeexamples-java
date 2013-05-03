package com.vogella.testing.easymock.first.test;

// Use static imports to 
// have direct access to these methods
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.vogella.testing.easymock.first.ICalcMethod;
import com.vogella.testing.easymock.first.IncomeCalculator;
import com.vogella.testing.easymock.first.Position;

public class IncomeCalculatorTest {

	private ICalcMethod calcMethod;
	private IncomeCalculator calc;

	@Before
	public void setUp() throws Exception {
		// NiceMocks return default values for
		// unimplemented methods
		calcMethod = createNiceMock(ICalcMethod.class);
		calc = new IncomeCalculator();
	}

	@Test
	public void testCalc1() {
		// Setting up the expected value of the method call calc
		expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0).times(2);
		expect(calcMethod.calc(Position.PROGRAMMER)).andReturn(50000.0);
		// Setup is finished need to activate the mock
		replay(calcMethod);

		calc.setCalcMethod(calcMethod);
		try {
			calc.calc();
			fail("Exception did not occur");
		} catch (RuntimeException e) {

		}
		calc.setPosition(Position.BOSS);
		assertEquals(70000.0, calc.calc(), 0);
		assertEquals(70000.0, calc.calc(), 0);
		calc.setPosition(Position.PROGRAMMER);
		assertEquals(50000.0, calc.calc(), 0);
		calc.setPosition(Position.SURFER);
		verify(calcMethod);
	}

	@Test(expected = RuntimeException.class)
	public void testNoCalc() {
		calc.setPosition(Position.SURFER);
		calc.calc();
	}

	@Test(expected = RuntimeException.class)
	public void testNoPosition() {
		expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0);
		replay(calcMethod);
		calc.setCalcMethod(calcMethod);
		calc.calc();
	}

	@Test(expected = RuntimeException.class)
	public void testCalc2() {
		// Setting up the expected value of the method call calc
		expect(calcMethod.calc(Position.SURFER)).andThrow(
				new RuntimeException("Don't know this guy")).times(1);

		// Setup is finished need to activate the mock
		replay(calcMethod);
		calc.setPosition(Position.SURFER);
		calc.setCalcMethod(calcMethod);
		calc.calc();
	}

}
