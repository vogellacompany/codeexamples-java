package de.vogella.dnd.jface.model;

import java.util.ArrayList;
import java.util.List;

public enum ContentProvider {
	INSTANCE;
	
	public List<Todo> getModel(){
		List<Todo> list = new ArrayList<Todo>();
		Todo todo = new Todo("Java", "Learn the Closure proposal");
		list.add(todo);
		todo = new Todo("Eclipse", "Learn more about the RCP platform");
		list.add(todo);
		return list;
	}
}
