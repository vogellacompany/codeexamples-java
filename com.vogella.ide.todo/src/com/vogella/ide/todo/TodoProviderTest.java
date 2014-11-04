package com.vogella.ide.todo;

import java.util.List;

public class TodoProviderTest {

	public static void main(String[] args) {
		List<Todo> model = TodoProvider.createInitialModel();
		if (model.size()!=5){
			throw new RuntimeException("size should be 5");
		} else {
			System.out.println("Correct");
		}
	}

}
