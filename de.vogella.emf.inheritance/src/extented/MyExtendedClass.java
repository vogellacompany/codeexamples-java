/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package extented;

import base.MyBaseClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>My Extended Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link extented.MyExtendedClass#getDetailedField <em>Detailed Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see extented.ExtentedPackage#getMyExtendedClass()
 * @model
 * @generated
 */
public interface MyExtendedClass extends MyBaseClass {
	/**
	 * Returns the value of the '<em><b>Detailed Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detailed Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detailed Field</em>' attribute.
	 * @see #setDetailedField(String)
	 * @see extented.ExtentedPackage#getMyExtendedClass_DetailedField()
	 * @model
	 * @generated
	 */
	String getDetailedField();

	/**
	 * Sets the value of the '{@link extented.MyExtendedClass#getDetailedField <em>Detailed Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Detailed Field</em>' attribute.
	 * @see #getDetailedField()
	 * @generated
	 */
	void setDetailedField(String value);

} // MyExtendedClass
