package test;

import java.util.ArrayList;

public class ArrayListTest2 {
	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<Person>();

		for (int i = 0; i < 1000; i++) {
			Person p = new Person("Firstname" + i, "Lastname " + i);
			list.add(p);
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getFirstName());
			System.out.println(list.get(i).getLastName());
		}
		// Kursform
		for (Person p : list) {
			System.out.println(p.getFirstName());
		}
	}
}
