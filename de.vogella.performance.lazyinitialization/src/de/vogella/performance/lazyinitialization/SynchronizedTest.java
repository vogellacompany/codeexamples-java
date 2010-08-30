package de.vogella.performance.lazyinitialization;

public class SynchronizedTest {
	private MyField myField;

	public synchronized MyField getMyField() {
		if (myField == null) {
			myField = new MyField();
		}
		return myField;
	}

}
