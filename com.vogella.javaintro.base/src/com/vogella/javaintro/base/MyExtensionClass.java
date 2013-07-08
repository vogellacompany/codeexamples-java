package com.vogella.javaintro.base;

class MyExtensionClass extends MyBaseClass {
	@Override
	void hello() {
		// optional
		// call super.hello() to call the method 
		// of the super class
		super.hello();
		// to something more
		System.out.println("Hello from MyExtensionClass");
	}
}
