package de.vogella.java.intro.poweroftwo;

/**
 * This class works different way the power of two (2^x)
 * 
 * @author vogella
 * 
 */

public class PowerOfTwo {
	public static void main(String[] args) {
		PowerOfTwo power = new PowerOfTwo();
		assert (power.getPower(0) == 1);
		assert (power.getPower(10) == 1024);
		assert (power.getPower(16) == 65536);
		System.out.println(power.getLargestPowerOfTwoWhichIsSmaller(1000));
		power.newMet();
	}

	/**
	 * Prints out all powers of 2 until n Returns the last power
	 */
	private int getPower(int n) {
		int i = 0;
		int power = 1;
		while (i < n) {
			i++;
			power *= 2;
			System.out
					.println("The power of 2^x for x = " + i + " is " + power);
		}
		return power;
	}

	private void newMet() {
		int i = 0;
		for (; i < 10;) {
			i++;
		}
		System.out.println("Funny");
	}

	private int getLargestPowerOfTwoWhichIsSmaller(int N) {
		int v = 2;
		int i = 0;
		while (v <= N) {
			i++;
			v *= 2;
		}
		return i;
	}
}
