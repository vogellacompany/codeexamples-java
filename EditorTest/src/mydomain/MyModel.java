package mydomain;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyModel {

	private List<Person> persons = new ArrayList<Person>();
	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	public class Person {

		private String firstName;

		private String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {

			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
			notifyListeners();
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public MyModel() {
		// Just for testing we hard-code the persons here:
		persons.add(new Person("Lars", "Vogel"));
		persons.add(new Person("Jim", "Knopf"));
	}

	private void notifyListeners() {
		for (Iterator iterator = listener.iterator(); iterator.hasNext();) {
			PropertyChangeListener name = (PropertyChangeListener) iterator
					.next();
			name.propertyChange(null);

		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

}
