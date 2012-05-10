package phonebook.model;

import java.util.ArrayList;
import java.util.List;

public class PhoneGroups extends AbstractModelObject {
	private final List/* <PhoneGroup> */m_groups = new ArrayList();

	public void addGroup(PhoneGroup group) {
		m_groups.add(group);
		firePropertyChange("groups", null, m_groups);
	}

	public void removeGroup(PhoneGroup group) {
		m_groups.remove(group);
		firePropertyChange("groups", null, m_groups);
	}

	public List getGroups() {
		return m_groups;
	}
}