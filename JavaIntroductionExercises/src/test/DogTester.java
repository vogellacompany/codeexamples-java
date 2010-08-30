package test;

public class DogTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Geburt vom Hund
		Dog myDog = new Dog("Jost", 30, 10);
		System.out.println(myDog.toString());
		// Growing
		myDog.werdeAelter();
		myDog.werdeAelter();
		myDog.werdeAelter();
		myDog.werdeAelter();
		myDog.werdeAelter();
		myDog.werdeAelter();

		System.out.println(myDog.toString());
	}
}
