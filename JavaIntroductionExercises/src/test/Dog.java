package test;

public class Dog {
	private String name;
	private int groesse;
	private int speed;

	public Dog(String name, int startGroesse, int speed) {
		this.name = name;

		groesse = startGroesse;
		this.speed = speed;
	}

	public void werdeAelter() {
		werdeSchneller();
		grow();
	}

	public void werdeSchneller() {
		speed += 1;
	}

	private void grow() {
		{
			groesse = groesse + 1;
		}

	}

	public String toString() {
		return "Ich heiﬂe " + name + " und bin " + groesse
				+ " cm groﬂ. Ich kann " + speed + " km pro Stunde laufen. ";
	}

}
