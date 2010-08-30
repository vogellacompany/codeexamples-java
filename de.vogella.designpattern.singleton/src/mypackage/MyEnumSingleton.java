package mypackage;

public enum MyEnumSingleton {
	INSTANCE;

	private int val = 0;

	// The following methods are just examples
	public void setValue(int val) {
		this.val = val;
	}

	public int getValue() {
		return val;
	}
	// other useful methods here
}
