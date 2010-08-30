package de.vogella.osgi.xinternal.consumer;

import de.vogella.osgi.xinternal.provider.Hello;

public class Test {
	public static void main(String[] args) {
		Hello hello = new Hello();
		System.out.println(hello.getHello());
	}

}
