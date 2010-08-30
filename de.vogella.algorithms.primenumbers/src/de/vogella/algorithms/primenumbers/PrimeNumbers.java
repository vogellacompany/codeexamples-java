package de.vogella.algorithms.primenumbers;

import java.util.ArrayList;
import java.util.List;

// Using the sieve of Eratosthenes
public class PrimeNumbers {
	public static List<Integer> calcPrimeNumbers(int n) {
		boolean[] isPrimeNumber = new boolean[n + 1]; // boolean defaults to
		// false
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < n; i++) {
			isPrimeNumber[i] = true;
		}
		for (int i = 2; i < n; i++) {
			if (isPrimeNumber[i]) {
				primes.add(i);
				// Now mark the multiple of i as non-prime number
				for (int j = i; j * i <= n; j++) {
					isPrimeNumber[i * j] = false;
				}
			}

		}

		return primes;
	}

	public static void main(String[] args) {
		List<Integer> calcPrimeNumbers = calcPrimeNumbers(21);
		for (Integer integer : calcPrimeNumbers) {
			System.out.println(integer);
		}
		System.out.println("Prime counting function (Pie(N)) : "
				+ calcPrimeNumbers.size());
	}
}
