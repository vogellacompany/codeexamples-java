package exercises.exercise24;

public class TestArray {
	public static void main(String[] args) {
		Person personArray[] = new Person[2];
		Person person = new Person("Lars", "Vogel");
		personArray[0] = person;
		person = new Person("Jim", "Knopf");
		personArray[1] = person;

		for (int i = 0; i < personArray.length; i++) {
			// Print the first name of the person
			System.out.println(personArray[i].getFirstName());
		}
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long used_memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println(used_memory);
	}
}
