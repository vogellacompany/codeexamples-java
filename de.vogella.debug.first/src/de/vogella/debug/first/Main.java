package de.vogella.debug.first;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Counter counter = new Counter();
		counter.count();
		System.out.println("We have counted " + counter.getResult());
	}

}
