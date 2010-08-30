package de.vogella.performance.lazyinitialization;

public class DoubleCheckItem {
	private volatile MyField myField;

	public MyField getMyField() {
		if (myField == null) {
			synchronized (this) {
				if (myField == null) {
					myField = new MyField();
				}
			}
		}
		return myField;
	}
}
