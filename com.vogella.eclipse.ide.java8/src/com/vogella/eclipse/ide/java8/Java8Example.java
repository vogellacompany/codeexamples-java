package com.vogella.eclipse.ide.java8;

public class Java8Example {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello Lambdas");
			}
		};
		
		new Thread(runnable);
		
	}
	
}
