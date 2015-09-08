package com.vogella.java.collections.list;

import java.util.ArrayList;
import java.util.List;

public class ListSorter {
	public static void main(String[] args) {
		System.out.println("Sorting with natural order");
		List<String> l1 = createList();
		l1.sort(null);
		l1.forEach(System.out::println);
		
		System.out.println("Sorting with a lamba expression for the comparison");
		List<String> l2 = createList();
		l2.sort((s1, s2) -> s1.compareToIgnoreCase(s2));  // sort ignoring case
		l2.forEach(System.out::println);
		
		System.out.println("Sorting with a method references");
		List<String> l3 = createList();
		l3.sort(String::compareToIgnoreCase);   
		l3.forEach(System.out::println);
	       
	}
	
	private static List<String>  createList() {
		List<String> list = new ArrayList<>();
		list.add("iPhone");
		list.add("Ubuntu");
		list.add("Android");
		list.add("Mac OS X");
		return list;
	}

}
