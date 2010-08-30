package de.vogella.wtp.jsp.model;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {
	public List<Name> getNames() {
		List<Name> list = new ArrayList<Name>();
		Name name = new Name();
		name.setFirstName("Jim");
		name.setLastName("Knopf");
		list.add(name);
		name = new Name();
		name.setFirstName("Jim");
		name.setLastName("Bean");
		list.add(name);
		return list;
	}
}
