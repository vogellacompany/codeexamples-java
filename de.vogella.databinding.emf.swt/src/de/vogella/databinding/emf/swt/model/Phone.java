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
 * A representation of the model object '<em><b>Phone</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.vogella.databinding.emf.swt.model.Phone#getNumber <em>Number</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPhone()
 * @model
 * @generated
 */
public interface Phone extends EObject {
	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(String)
	 * @see de.vogella.databinding.emf.swt.model.ModelPackage#getPhone_Number()
	 * @model
	 * @generated
	 */
	String getNumber();

	/**
	 * Sets the value of the '{@link de.vogella.databinding.emf.swt.model.Phone#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(String value);

} // Phone
