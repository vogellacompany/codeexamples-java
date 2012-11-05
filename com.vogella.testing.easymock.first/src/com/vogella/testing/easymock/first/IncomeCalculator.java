package com.vogella.testing.easymock.first;

public class IncomeCalculator {

	private ICalcMethod calcMethod;
	private Position position;

	public void setCalcMethod(ICalcMethod calcMethod) {
		this.calcMethod = calcMethod;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public double calc() {
		if (calcMethod == null) {
			throw new RuntimeException("CalcMethod not yet maintained");
		}
		if (position == null) {
			throw new RuntimeException("Position not yet maintained");
		}
		return calcMethod.calc(position);
	}
}
