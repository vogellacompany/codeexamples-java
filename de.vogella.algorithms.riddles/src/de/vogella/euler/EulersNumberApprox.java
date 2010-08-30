package de.vogella.euler;

/**
 * Calculates a approximation of the euler's number The euler's number is
 * defined as
 * <p>
 * e = 1 + 1 / 1! + 1/2! + 1/3! + 1/4! + 1/5!...
 * 
 * @author Lars Vogel
 * 
 */
public class EulersNumberApprox {

	public static void main(String[] args) {
		System.out.println(euler());
	}

	private static double euler() {
		double sum = 1;
		long divisor = 1;

		for (int i = 1; i < 50; i++) {
			divisor *= i;
			sum += 1.0 / divisor;
		}
		Math.exp(1);
		return sum;
	}
}
