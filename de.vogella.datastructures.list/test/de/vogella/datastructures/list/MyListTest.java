package de.vogella.datastructures.list;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyListTest {


	@Test(expected=IndexOutOfBoundsException.class)
	public void testMyList() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(4);
		assertTrue(4 == list.get(4));
		assertTrue(2 == list.get(1));
		assertTrue(3 == list.get(2));
		
		list.get(6);
	}
	

	@Test(expected=IndexOutOfBoundsException.class)
	public void testNegative() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(4);
		list.get(-1);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(4);
		assertTrue(4 == list.get(4));
		assertTrue(2 == list.get(1));
		assertTrue(3 == list.get(2));
		
		list.get(6);
	}
	
	
	
}
