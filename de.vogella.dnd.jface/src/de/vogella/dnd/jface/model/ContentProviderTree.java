package de.vogella.dnd.jface.model;

import java.util.ArrayList;
import java.util.List;

public enum ContentProviderTree {
	INSTANCE;
	List<String> list = new ArrayList<String>();
	
	private ContentProviderTree() {
		list.add("Branch1");
		list.add("Branch1");
	}
	
	public List<String> getModel(){
		return list;
	}
}
