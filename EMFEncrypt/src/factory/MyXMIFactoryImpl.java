package factory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.AESCipherImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class MyXMIFactoryImpl extends XMIResourceFactoryImpl {

	@Override
	public Resource createResource(URI uri) {
		XMIResourceFactoryImpl resFactory = new XMIResourceFactoryImpl();
		XMIResource resource = (XMIResource) resFactory.createResource(uri);
		try {
			resource.getDefaultLoadOptions().put(Resource.OPTION_CIPHER,
					new AESCipherImpl("12345"));
			resource.getDefaultSaveOptions().put(Resource.OPTION_CIPHER,
					new AESCipherImpl("12345"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}
}
