package phonebook.model;

import java.util.ArrayList;
import java.util.List;

public class PhoneGroup extends AbstractModelObject {
	private final List/* <Person> */m_persons = new ArrayList();
	private String m_name;

	public PhoneGroup() {
	}

	public PhoneGroup(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		String oldValue = m_name;
		m_name = name;
		firePropertyChange("name", oldValue, m_name);
	}

	public void addPerson(Person person) {
		m_persons.add(person);
		firePropertyChange("persons", null, m_persons);
	}

	public void removePerson(Person person) {
		m_persons.remove(person);
		firePropertyChange("persons", null, m_persons);
	}

	public List getPersons() {
		return m_persons;
	}
	
	public String getEmail() {
		return "";
	}

	public void setEmail(String email) {
	}

	public String getPhone() {
		return "";
	}

	public void setPhone(String phone) {
	}

	public String getMobilePhone1() {
		return "";
	}

	public void setMobilePhone1(String phone1) {
	}

	public String getMobilePhone2() {
		return "";
	}

	public void setMobilePhone2(String phone2) {
	}
}