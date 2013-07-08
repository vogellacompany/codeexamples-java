package com.vogella.javaintro.array;

public class TestMain {
	public static void main(String[] args) {
		// declares an array of integers
		int[] array;

		// allocates memory for 10 integers
		array = new int[10];

		// initialize values
		array[0] = 10;
		// initialize second element
		array[1] = 20;
		array[2] = 30;
		array[3] = 40;
		array[4] = 50;
		array[5] = 60;
		array[6] = 70;
		array[7] = 80;
		array[8] = 90;
		array[9] = 100;

		for (int i : array) {
			System.out.println("Element at index " + i + " :"  + array[0]);
		}
	}
}
