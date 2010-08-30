package de.vogella.jface.treeviewer.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private int sort;
	private List<Todo> todos = new ArrayList<Todo>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public List<Todo> getTodos (){
		return todos;
	}
	
	
}
