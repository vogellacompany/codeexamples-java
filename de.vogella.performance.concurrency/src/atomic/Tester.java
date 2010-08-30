package atomic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester {
	List<Integer> numbers = new ArrayList<Integer>();
	private List<Thread> threads;

	public void test() {
		threads = new ArrayList<Thread>();
		for (int i = 0; i < 4000; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					numbers.add(NextNumberWrong.getNext());
				}
			});
			threads.add(thread);
			thread.start();
		}
	}

	public void check() {
		boolean wait = false;
		do {
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					wait = true;
				}
			}
		} while (wait);

		Collections.sort(numbers);
		// for (int i = 0; i < numbers.size() - 1; i++) {
		// if (numbers.get(i).equals(numbers.get(i + 1))) {
		// throw new RuntimeException("We have a collision");
		// }
		// }
		System.out.println("No double number");
	}

	public static void main(String[] args) {
		Tester test = new Tester();
		test.test();
		test.check();
	}
}
