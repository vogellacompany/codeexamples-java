package observers;

import model.IPerson;
import model.IPersonList;
import model.ModelFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ElementObserver {
	private IPersonList persons;

	public ElementObserver() {
		ModelFactory factory = ModelFactory.eINSTANCE;
		persons = factory.createIPersonList();

		Adapter adapter = new AdapterImpl() {
			public void notifyChanged(Notification notification) {
				System.out
						.println("Notfication received from the data model. Data model has changed!!!");
			}
		};
		persons.eAdapters().add(adapter);
	}

	public void doStuff() {
		ModelFactory factory = ModelFactory.eINSTANCE;
		IPerson person = factory.createIPerson();
		person.setFirstName("Lars");
		System.out.println("I'm adding a person.");
		persons.getPersons().add(person);
		System.out.println("I'm changing a entry");
		persons.getPersons().get(0).setFirstName("Lars2");
	}
}
