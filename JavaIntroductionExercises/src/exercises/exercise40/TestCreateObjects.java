package exercises.exercise40;

import java.util.ArrayList;

public class TestCreateObjects {
	private static final long MAX = 10000000;

	public void create() {
		System.out.println("First only the objects");
		for (long i = 0; i <= MAX; i++) {
			MinMax object = new MinMax();
			object.setMin(100);
			object.setMin(200);
			if ((i % 100000) == 0) {
				System.out.println("Number of objects " + i);
			}
		}
	}

	public void createList() {
		System.out.println("Now the list");
		ArrayList<MinMax> list = new ArrayList<MinMax>();
		for (long i = 0; i <= MAX; i++) {
			MinMax object = new MinMax();
			object.setMin(100);
			object.setMin(200);
			list.add(object);
			if ((i % 100000) == 0) {
				System.out.println("Number of objects " + i);
			}
		}
	}
}
