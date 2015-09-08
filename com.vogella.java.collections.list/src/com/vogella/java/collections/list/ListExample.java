package com.vogella.java.collections.list;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}

}
