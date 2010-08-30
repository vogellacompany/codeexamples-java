/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package extented.impl;

import extented.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtentedFactoryImpl extends EFactoryImpl implements ExtentedFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtentedFactory init() {
		try {
			ExtentedFactory theExtentedFactory = (ExtentedFactory)EPackage.Registry.INSTANCE.getEFactory("http:/www.vogella.de/extended/"); 
			if (theExtentedFactory != null) {
				return theExtentedFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExtentedFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtentedFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ExtentedPackage.MY_EXTENDED_CLASS: return createMyExtendedClass();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MyExtendedClass createMyExtendedClass() {
		MyExtendedClassImpl myExtendedClass = new MyExtendedClassImpl();
		return myExtendedClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtentedPackage getExtentedPackage() {
		return (ExtentedPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExtentedPackage getPackage() {
		return ExtentedPackage.eINSTANCE;
	}

} //ExtentedFactoryImpl
