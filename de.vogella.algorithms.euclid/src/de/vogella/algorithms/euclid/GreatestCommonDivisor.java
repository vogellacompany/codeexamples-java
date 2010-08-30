package de.vogella.algorithms.euclid;

/**
 * Calculates the greatest common divisor for two numbers.
 * <p>
 * Based on the fact that the gcd from p and q is the same as the gcd from p and
 * p % q in case p is larger then q
 * 
 * @author Lars Vogel
 * 
 */
public class GreatestCommonDivisor {
	public static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}
		return gcd(q, p % q);
	}

	// Test enable assert check via -ea as a VM argument

	public static void main(String[] args) {
		assert (gcd(4, 16) == 4);
		assert (gcd(16, 4) == 4);
		assert (gcd(15, 60) == 15);
		assert (gcd(15, 65) == 5);
		assert (gcd(1052, 52) == 4);
	}
}
