package de.vogella.jface.treeviewer.model;

import java.util.ArrayList;
import java.util.List;

public class TodoMockModel  {

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		Category category = new Category();
		category.setName("Programming");
		categories.add(category);
		Todo todo = new Todo("Write more about e4");
		category.getTodos().add(todo);
		todo = new Todo("Android", "Write a widget.");
		category.getTodos().add(todo);
		
		category = new Category();
		category.setName("Leasure");
		categories.add(category);
		todo = new Todo("Skiing");
		category.getTodos().add(todo);
		
		return categories;
	}

}
