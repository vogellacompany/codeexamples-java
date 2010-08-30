package test;

public class Tester01 {
	public static void main(String[] args) {
		int j = 0;
		for (int i = 0; i <= 3; i++) {

			System.out.println("i ist " + i);
			System.out.println("j ist " + j);
			j = j + i;
			System.out.println("j ist nach der Addition " + j);
			System.out.println("i ist noch " + i);
		}

		System.out.println("for result " + j);

		j = 0;
		int i = 0;
		boolean check = true;
		while (check) {
			if (i <= 3) {
				System.out.println("i ist " + i);
				System.out.println("j ist " + j);
				j = j + i;

				System.out.println("i ist " + i);
				System.out.println("j ist " + j);

			} else {
				check = false;
			}
			i++;
		}
		System.out.println("while brings me " + j);
	}
}
