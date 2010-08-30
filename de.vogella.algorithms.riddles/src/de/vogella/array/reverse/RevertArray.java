package de.vogella.array.reverse;

/**
 * Reverts the order of a array without using an additional array
 * 
 * @author Lars Vogel
 * 
 */
public class RevertArray {

	public static void revert(int[] a) {
		for (int i = 0; i <= a.length / 2; i++) {
			int temp = a[i];
			a[i] = a[a.length - 1 - i];
			a[a.length - 1 - i] = temp;
		}
	}

	public static void main(String[] args) {
		int[] a = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		RevertArray.revert(a);
		for (int i = 1; i <= 9; i++) {
			assert (a[i - 1] == i);
		}
	}
}
