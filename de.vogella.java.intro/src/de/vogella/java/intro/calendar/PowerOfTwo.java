package de.vogella.java.intro.calendar;


public class PowerOfTwo {
	public static void main(String[] args) {
		PowerOfTwo power = new PowerOfTwo();
		power.createSequence();
		System.out.println(power.getLargestPowerOfTwoWhichIsSmaller(1000));
		power.newMet();
	}

	private void createSequence() {
		int i = 0;
		int v = 1;
		while (i <= 16) {
			System.out.println("2 hoch " + i + " ist " + v);
			i = i + 1;
			v = v * 2;
		}
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
