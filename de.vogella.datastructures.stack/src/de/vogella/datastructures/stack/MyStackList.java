package de.vogella.datastructures.stack;

import java.util.ArrayList;

public class MyStackList<E> extends ArrayList<E> {

	private static final long serialVersionUID = 1L;

	public E pop() {
		E e = get(size() - 1);
		remove(size() - 1);
		return e;
	}

	public void push(E e) {
		add(e);
	}

}
