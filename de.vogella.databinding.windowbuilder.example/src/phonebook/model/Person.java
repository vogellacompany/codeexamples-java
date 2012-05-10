package phonebook.model;

public class Person extends AbstractModelObject {
	private String m_name = "";
	private String m_email = "";
	private String m_phone = "";
	private String m_mobilePhone1 = "";
	private String m_mobilePhone2 = "";

	public Person() {
	}

	public Person(String name, String email, String phone, String phone1,
			String phone2) {
		m_name = name;
		m_email = email;
		m_phone = phone;
		m_mobilePhone1 = phone1;
		m_mobilePhone2 = phone2;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		String oldValue = m_name;
		m_name = name;
		firePropertyChange("name", oldValue, m_name);
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(String email) {
		String oldValue = m_email;
		m_email = email;
		firePropertyChange("email", oldValue, m_email);
	}

	public String getPhone() {
		return m_phone;
	}

	public void setPhone(String phone) {
		String oldValue = m_phone;
		m_phone = phone;
		firePropertyChange("phone", oldValue, m_phone);
	}

	public String getMobilePhone1() {
		return m_mobilePhone1;
	}

	public void setMobilePhone1(String phone1) {
		String oldValue = m_mobilePhone1;
		m_mobilePhone1 = phone1;
		firePropertyChange("mobilePhone1", oldValue, m_mobilePhone1);
	}

	public String getMobilePhone2() {
		return m_mobilePhone2;
	}

	public void setMobilePhone2(String phone2) {
		String oldValue = m_mobilePhone2;
		m_mobilePhone2 = phone2;
		firePropertyChange("mobilePhone2", oldValue, m_mobilePhone2);
	}
}