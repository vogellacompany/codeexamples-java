package test;

public class Duck {
	// Variable
	private String name;
	private boolean canFly;

	// Constructor
	public Duck(String name1, boolean canFly1) {
		canFly = canFly1;
		name = name1;
	}

	// Methode
	public void quack(boolean doQuack) {
		if (doQuack) {
			canFly = true;
			System.out.println("Quack");
		} else {
			System.out.println("Not");
		}
	}

	// Methode
	public void fly() {
		if (canFly) {
			System.out.println("I'm flying....");
		} else {
			System.out.println("Yeah, I'm waking....");
		}
	}

	// Methode
	public String getName() {
		return name;
	}

	// Methode
	public void setName(String name) {
		this.name = name;
	}

}
