package de.vogella.java.intro.prime;

/**
 * Calculates if the value n is a prime number
 */

public class PrimeChecker {
	public boolean isPrimeNumber(int n) {
		if (n == 1) {
			return false;
		} else {
			return testDivision(n, 2);
		}
	}

	private boolean testDivision(int n, int i) {
		if (i == n) {
			return true;
		}
		if ((n % i) == 0) {
			return false;
		} else {
			return testDivision(n, i + 1);
		}
	}
}
