/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package extented;

import base.BasePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see extented.ExtentedFactory
 * @model kind="package"
 * @generated
 */
public interface ExtentedPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "extented";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:/www.vogella.de/extended/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "extented";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtentedPackage eINSTANCE = extented.impl.ExtentedPackageImpl.init();

	/**
	 * The meta object id for the '{@link extented.impl.MyExtendedClassImpl <em>My Extended Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see extented.impl.MyExtendedClassImpl
	 * @see extented.impl.ExtentedPackageImpl#getMyExtendedClass()
	 * @generated
	 */
	int MY_EXTENDED_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_EXTENDED_CLASS__ID = BasePackage.MY_BASE_CLASS__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_EXTENDED_CLASS__DESCRIPTION = BasePackage.MY_BASE_CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Detailed Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_EXTENDED_CLASS__DETAILED_FIELD = BasePackage.MY_BASE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>My Extended Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MY_EXTENDED_CLASS_FEATURE_COUNT = BasePackage.MY_BASE_CLASS_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link extented.MyExtendedClass <em>My Extended Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>My Extended Class</em>'.
	 * @see extented.MyExtendedClass
	 * @generated
	 */
	EClass getMyExtendedClass();

	/**
	 * Returns the meta object for the attribute '{@link extented.MyExtendedClass#getDetailedField <em>Detailed Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Detailed Field</em>'.
	 * @see extented.MyExtendedClass#getDetailedField()
	 * @see #getMyExtendedClass()
	 * @generated
	 */
	EAttribute getMyExtendedClass_DetailedField();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExtentedFactory getExtentedFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link extented.impl.MyExtendedClassImpl <em>My Extended Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see extented.impl.MyExtendedClassImpl
		 * @see extented.impl.ExtentedPackageImpl#getMyExtendedClass()
		 * @generated
		 */
		EClass MY_EXTENDED_CLASS = eINSTANCE.getMyExtendedClass();

		/**
		 * The meta object literal for the '<em><b>Detailed Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MY_EXTENDED_CLASS__DETAILED_FIELD = eINSTANCE.getMyExtendedClass_DetailedField();

	}

} //ExtentedPackage
