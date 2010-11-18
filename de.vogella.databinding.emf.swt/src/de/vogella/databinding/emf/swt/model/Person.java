/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.databinding.emf.swt.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Person#getFirstName <em>First Name</em>}</li>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Person#getLastName <em>Last Name</em>}</li>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Person#getGender <em>Gender</em>}</li>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Person#isIsMarried <em>Is Married</em>}</li>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Person#getPhone <em>Phone</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see #setFirstName(String)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson_FirstName()
	 * @model
	 * @generated
	 */
	String getFirstName();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Person#getFirstName <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Name</em>' attribute.
	 * @see #getFirstName()
	 * @generated
	 */
	void setFirstName(String value);

	/**
	 * Returns the value of the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Name</em>' attribute.
	 * @see #setLastName(String)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson_LastName()
	 * @model
	 * @generated
	 */
	String getLastName();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Person#getLastName <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Name</em>' attribute.
	 * @see #getLastName()
	 * @generated
	 */
	void setLastName(String value);

	/**
	 * Returns the value of the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gender</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gender</em>' attribute.
	 * @see #setGender(String)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson_Gender()
	 * @model
	 * @generated
	 */
	String getGender();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Person#getGender <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gender</em>' attribute.
	 * @see #getGender()
	 * @generated
	 */
	void setGender(String value);

	/**
	 * Returns the value of the '<em><b>Is Married</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Married</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Married</em>' attribute.
	 * @see #setIsMarried(boolean)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson_IsMarried()
	 * @model
	 * @generated
	 */
	boolean isIsMarried();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Person#isIsMarried <em>Is Married</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Married</em>' attribute.
	 * @see #isIsMarried()
	 * @generated
	 */
	void setIsMarried(boolean value);

	/**
	 * Returns the value of the '<em><b>Phone</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phone</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phone</em>' reference.
	 * @see #setPhone(Phone)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPerson_Phone()
	 * @model
	 * @generated
	 */
	Phone getPhone();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Person#getPhone <em>Phone</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phone</em>' reference.
	 * @see #getPhone()
	 * @generated
	 */
	void setPhone(Phone value);

} // Person
