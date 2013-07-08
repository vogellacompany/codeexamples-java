package com.vogella.javaintro.statementif;

public class ExampleIfStatemet {
	public void tester(int number) {

		if (number > 10) {
			System.out.println("Input is larger than 10");
		}
	}

	public void tester2(int number) {

		if (number > 10) {
			System.out.println("Input is larger than 10");
		} else {
			System.out.println("Input is smaller or equal than 10");
		}
	}
}
