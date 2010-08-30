package main;

import mypackage.MyEnumSingleton;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyEnumSingleton instance = MyEnumSingleton.INSTANCE;
		instance.setValue(10);
		System.out.println(instance.getValue());
		// This should write 10
		System.out.println(MyEnumSingleton.INSTANCE.getValue());
		// This should also write 10
		MyEnumSingleton newInstance = MyEnumSingleton.INSTANCE;
		System.out.println(newInstance.getValue());
	}
}
