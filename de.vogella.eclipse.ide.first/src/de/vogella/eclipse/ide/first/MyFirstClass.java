package de.vogella.eclipse.ide.first;

public class MyFirstClass {

	private static final String HELLO = "Hello Eclipse!";

	public static void main(String[] args) {
		// TODO Provide user interface
		System.out.println(HELLO);
		int sum = 0;
		sum = calculateSum(sum);
		System.out.println(sum);
	}

	private static int calculateSum(int sum) {
		for (int i = 0; i <= 100; i++) {
			sum += i;
		}

		try {

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

		return sum;
	}
}
