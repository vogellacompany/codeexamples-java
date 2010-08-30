package exercises.exercise40;

public final class Main {
	// Avoid that someone creates an instance of this class
	private Main() {
	}

	/**
	 * @param args
	 */
	// This will
	public static void main(String[] args) {
		TestCreateObjects test = new TestCreateObjects();
		// Will we get a out-of-memory error?
		test.create();
		// Will we get a out-of-memory error?
		test.createList();
	}
}
