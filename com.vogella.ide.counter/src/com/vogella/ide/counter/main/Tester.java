package com.vogella.ide.counter.main;

import com.vogella.ide.counter.util.Counter;

public class Tester {

	public static void main(String[] args) {
		Counter counter = new Counter();
		int result = counter.count(5);
		if (result == 120) {
			System.out.println("Correct");
		} else {
			System.out.println("False");
		}
		try {
			counter.count(256);
		} catch (RuntimeException e) {
			System.out.println("Works as exepected");
		}
	}

}
