package exercises.exercise06;

class Person {
	int age = 12;
	String firstName;
	String lastName;

	public Person(String a, String b) {
		firstName = a;
		lastName = b;
	}

	void writeName() {
		System.out.println(firstName + " " + lastName + "" + age);
	}
}
