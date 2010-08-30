package de.vogella.algorithms.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleList {
	public static void shuffleList(List<Integer> a) {
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	private static void swap(List<Integer> a, int i, int change) {
		int helper = a.get(i);
		a.set(i, a.get(change));
		a.set(change, helper);
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		shuffleList(list);
		for (int i : list) {
			System.out.println(i);
		}
	}
}
