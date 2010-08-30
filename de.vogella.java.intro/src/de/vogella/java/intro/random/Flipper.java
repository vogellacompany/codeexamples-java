package de.vogella.java.intro.random;


public class Flipper {

	public static void main(String[] args) {
		Flipper flip = new Flipper();
		flip.flip(10000000);
	}

	private void flip(int i) {
		int left = 0;
		int right = 0;
		for (int j = 0; j < i; j++) {
			if (Math.random() < 0.5) {
				left++;
			} else {
				right++;
			}
		}
		System.out.println(left);
		System.out.println(right);
	}
}
