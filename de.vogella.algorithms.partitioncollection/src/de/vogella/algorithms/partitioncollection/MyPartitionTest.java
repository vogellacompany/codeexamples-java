package de.vogella.algorithms.partitioncollection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class MyPartitionTest {
	@Test
	public void partitiontest1() {
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		list.add("six");
		list.add("seven");
		list.add("eight");
		list.add("nine");
		list.add("ten");
		list.add("eleven");
		List<List<String>> partition = MyPartition.partition(list, 1);
		System.out.println(partition.get(2).size()); 
		assertTrue(partition.size()==11);
		assertTrue(partition.get(0).size()==1);
		partition = MyPartition.partition(list, 7);
		assertTrue(partition.size()==2);
		assertTrue(partition.get(0).size()==7);
		assertTrue(partition.get(1).size()==4);
		
		// Now let assume you want to have x number of buckets
		// How many elements must placed in a list?
		// Take x as 3
		
		int buckets = 3;
		int divide = list.size() / buckets;  
		if (list.size() % buckets !=0){
			divide ++;
		}
		
		
		System.out.println("Max. number of element in each bucket " + divide);
		partition = MyPartition.partition(list, divide );
		assertTrue(partition.size()==buckets);
	}
}
