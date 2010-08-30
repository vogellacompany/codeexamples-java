package load;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import mymodel.IPerson;
import mymodel.MymodelFactory;
import mymodel.impl.MymodelPackageImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import factory.MyXMIFactoryImpl;

public class Create {
	public void create() {
		MymodelPackageImpl.init();
		// Retrieve the default factory singleton
		MymodelFactory factory = MymodelFactory.eINSTANCE;

		// Create the content of the model via this program
		IPerson person = factory.createIPerson();
		person.setLastname("Lars");

		// Register the XMI resource factory for the .website extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("person", new MyXMIFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resource = resSet.createResource(URI
		.createURI("mymodel.person"));
		resource.getContents().add(person);

		// Now save the content.
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void load() {
		// Initialize the model
		MymodelPackageImpl.init();
		// Register the XMI resource factory for the .website extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("person", new MyXMIFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.getResource(URI
				.createURI("mymodel.person"), true);
		try {
			IPerson person= (IPerson) resource.getContents().get(0);
			System.out.println(person.getLastname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Create test = new Create();
		test.create();
		test.load();
	}
}
