package de.vogella.performance.lazyinitialization;

public class DoubleCheckItemImproved {
	private volatile MyField myField;

	public MyField getMyField() {
		MyField tmp = myField;
		if (tmp == null) {
			synchronized (this) {
				tmp = myField;
				if (tmp == null) {
					myField = tmp = new MyField();
				}
			}
		}
		return tmp; // Using tmp here instead of myField avoids an memory update
	}
}
