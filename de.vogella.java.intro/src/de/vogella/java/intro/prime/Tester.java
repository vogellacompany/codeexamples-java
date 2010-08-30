package de.vogella.java.intro.prime;

public class Tester {

	public static void main(String[] args) {
		PrimeChecker check = new PrimeChecker();
		System.out.println(check.isPrimeNumber(1));
		System.out.println(check.isPrimeNumber(2));
		System.out.println(check.isPrimeNumber(3));
		System.out.println(check.isPrimeNumber(4));
		System.out.println(check.isPrimeNumber(5));
		System.out.println(check.isPrimeNumber(6));
		System.out.println(check.isPrimeNumber(7));
		System.out.println(check.isPrimeNumber(11));
		System.out.println(check.isPrimeNumber(13));
		System.out.println(check.isPrimeNumber(21));
	}

}
