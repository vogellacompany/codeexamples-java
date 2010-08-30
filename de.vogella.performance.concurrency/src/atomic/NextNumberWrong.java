package atomic;

public class NextNumberWrong {
	private static volatile int nextNumber = 0;

	public static int getNext() {
		return nextNumber++;
	}
}
