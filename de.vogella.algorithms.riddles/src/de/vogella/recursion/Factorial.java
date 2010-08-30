package de.vogella.recursion;

public class Factorial {
	public static long factor(long n) {
		// Base case
		if (n == 1) {
			return n;
		}
		// Reductions steps
		return n * factor(n - 1);
	}

	public static void main(String[] args) {
		assert (factor(5) == 120);
		assert (factor(6) == 720);
	}
}
