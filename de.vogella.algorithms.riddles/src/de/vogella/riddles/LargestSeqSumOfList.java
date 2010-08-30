package de.vogella.riddles;

import java.util.Random;

/**
 * Calculates the largest sum of sequential subset in an array of numbers
 * 
 * @author Lars Vogel
 * 
 */
public class LargestSeqSumOfList {

	/**
	 * Simple approach, just calculate all possible sums and pick the highest.
	 * This is performance intensive but does not require a lot of memory.
	 */
	public int getSum(int[] a) {
		int start = 0;
		int end = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			int temp = 0;
			for (int j = i; j < a.length; j++) {
				temp += a[j];
				if (temp > sum) {
					start = i;
					end = j;
					sum = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println("Start " + start);
		System.out.println("End " + end);
		System.out.println("Sum " + sum);
		return sum;
	}

	public int getSum2(int[] a) {
		int[] sums = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				sums[i] = a[i];
			} else {
				sums[i] = sums[i - 1] + a[i];
			}
		}
		for (int i : sums) {
			System.out.println("Partial sum " + i);
		}
		// Find the highest number from right, everything right from the highest
		// number in the list can get ignored as the following number only
		// increase the values.
		int end = sums.length - 1;
		for (int i = sums.length - 2; i >= 0; i--) {
			if (sums[end] < sums[i]) {
				end = i;
			}
		}
		int start = 0;
		for (int i = 0; i < end; i++) {
			// If the total sum get negative the whole can get ignored
			if (sums[i] < 0) {
				start = i + 1;
			}
		}
		System.out.println("Start " + start);
		System.out.println("End " + end);
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += a[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		LargestSeqSumOfList test = new LargestSeqSumOfList();
		int[] a = { -5, 20, -4, 10, -18 }; // 20, -4 , 10
		int[] b = { -9, 19, -20, 12, 9, -3, 2 }; // 12, 9
		int[] c = { 1, 3, 5, 7, 9, -2, -1 }; // 1, 3, 5, 7, 9
		int[] d = { -10, 20, 10, -8, 9, -9, 0 }; // 20, 10, -8, 9
		int[] e = { -10, 20, -10 }; // 20
		int[] f = { 20, 10, -8, 9, -9, 0 }; // 20, 10, -8, 9
		assert (test.getSum(a) == 26);
		assert (test.getSum2(a) == 26);
		assert (test.getSum(b) == 21);
		assert (test.getSum2(b) == 21);
		assert (test.getSum(c) == 25);
		assert (test.getSum2(c) == 25);
		assert (test.getSum(d) == 31);
		assert (test.getSum2(d) == 31);
		assert (test.getSum(e) == 20);
		assert (test.getSum2(e) == 20);
		assert (test.getSum(f) == 31);
		assert (test.getSum2(f) == 31);

		// Random Tests

		int[] values = new int[10];
		for (int i = 0; i < values.length; i++) {
			Random random = new Random();
			int nextInt = random.nextInt(101);
			if (Math.random() > 0.5) {
				nextInt *= -1;
			}
			values[i] = nextInt;
		}
		assert (test.getSum(values) == test.getSum2(values));

	}
}
