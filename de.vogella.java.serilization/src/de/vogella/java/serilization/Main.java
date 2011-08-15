package de.vogella.java.serilization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	public static void main(String[] args) {
		String filename = "time.ser";
		Person p = new Person("Lars", "Vogel");

		// Save the object to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(p);

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Read the object from file
		// Save the object to file
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			p = (Person) in.readObject();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(p);
	}
}
