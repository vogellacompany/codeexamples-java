package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import domainmodel.Family;
import domainmodel.Person;

public class Main {
	public static void main(String[] args) {
		// Instantiate the JAXB context. The context path
		// indicates which classes are involved in the XML binding
		try {
			JAXBContext context = JAXBContext.newInstance("domainmodel.Person");
			List<Person> list = new ArrayList<Person>();
			Person person = new Person();
			// Create Testdata
			Family family = new Family();
			family.setDescription("Familie Vogel");
			person.setFirstName("Lars");
			person.setLastName("Vogel");
			person.setFamily(family);
			list.add(person);

			Person person2 = new Person();
			person2.setFirstName("Nadja");
			person2.setLastName("Vogel");
			person2.setFamily(family);
			list.add(person2);

			// Marshal the objects to XML and write to standard output
			// Unmarshal the objects from XML
			File file = new File("output.xml");
			Marshaller marshaller;

			marshaller = context.createMarshaller();
			// JAXBElement<Person> bookingElement = (new ObjectFactory())
			// .createBooking(Person);

			marshaller.marshal(person2, System.out);
			// Now to file
			// marshaller.marshal(list, file);

			// Unmarshaller unmarshaller = context.createUnmarshaller();
			// List<Person> newList = new ArrayList<Person>();
			// newList = (Person) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
