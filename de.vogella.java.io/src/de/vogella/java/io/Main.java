package de.vogella.java.io;

public class Main {
	public static void main(String[] args) {
		MyFile myFile = new MyFile();
		String input = myFile.readTextFile("Testing.txt");
		String output = input.replace("@Kontaktanschrift", "This is cool\\\\");
		System.out.println(output);
		myFile.writeTextFile("Testing2.txt", output);
	}
}
