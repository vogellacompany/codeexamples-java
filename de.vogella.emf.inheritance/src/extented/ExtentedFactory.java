/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package extented;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see extented.ExtentedPackage
 * @generated
 */
public interface ExtentedFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtentedFactory eINSTANCE = extented.impl.ExtentedFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>My Extended Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>My Extended Class</em>'.
	 * @generated
	 */
	MyExtendedClass createMyExtendedClass();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExtentedPackage getExtentedPackage();

} //ExtentedFactory
