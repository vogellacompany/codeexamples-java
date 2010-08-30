package observers;

import model.IPerson;
import model.IPersonList;
import model.ModelFactory;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class TotalObserver {
	private IPersonList persons;

	public TotalObserver() {
		ModelFactory factory = ModelFactory.eINSTANCE;
		persons = factory.createIPersonList();

		EContentAdapter adapter = new EContentAdapter() {
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
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
		IPerson person2 = persons.getPersons().get(0);
		person2.setFirstName("Lars2");

	}
}
