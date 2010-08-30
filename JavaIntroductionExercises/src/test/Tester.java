package test;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Duck myDuck1 = new Duck("Piffi", false);

		myDuck1.fly();
		myDuck1.quack(true);
		myDuck1.fly();

	}

}
