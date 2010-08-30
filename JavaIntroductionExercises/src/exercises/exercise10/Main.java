package exercises.exercise10;

// This class defines the main method and nothing else
// The "real" object is person
public final class Main {
	private Main() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person person = new Person("Lars", "Vogel");
		person.setFirstName("Jim");
		person.setLastName("Knopf");
	}
}
