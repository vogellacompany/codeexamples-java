package domainmodel;

import java.util.ArrayList;
import java.util.List;



public class Family {
	
	private String description;
	private List<Person> members = new ArrayList<Person>();


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Person> getMembers() {
		return members;
	}

}
