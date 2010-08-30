package test;

public class TestWhile {
	public void sumItUp(int zahl) {
		int sum = 0;
		int i = 1;
		while (i <= zahl) {
			sum = sum + i;
			System.out.println(sum);
			i++;
		}
		System.out.println("Fertig");
	}

	public static void main(String[] args) {
		TestWhile test = new TestWhile();
		test.sumItUp(2);
	}
}
