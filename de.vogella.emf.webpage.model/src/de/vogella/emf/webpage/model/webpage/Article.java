/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.emf.webpage.model.webpage;

import java.util.Calendar;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Article</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.vogella.emf.webpage.model.webpage.Article#getName <em>Name</em>}</li>
 *   <li>{@link de.vogella.emf.webpage.model.webpage.Article#getCreated <em>Created</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.vogella.emf.webpage.model.webpage.WebpagePackage#getArticle()
 * @model
 * @generated
 */
public interface Article extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.vogella.emf.webpage.model.webpage.WebpagePackage#getArticle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.vogella.emf.webpage.model.webpage.Article#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(Calendar)
	 * @see de.vogella.emf.webpage.model.webpage.WebpagePackage#getArticle_Created()
	 * @model dataType="de.vogella.emf.webpage.model.webpage.calendar"
	 * @generated
	 */
	Calendar getCreated();

	/**
	 * Sets the value of the '{@link de.vogella.emf.webpage.model.webpage.Article#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(Calendar value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='if (eIsProxy()) return super.toString();\r\n\t\tStringBuffer result = new StringBuffer(super.toString());\r\n\t\tresult.append(\"Article: \");\r\n\t\tresult.append(name);\r\n\t\treturn result.toString();'"
	 * @generated
	 */
	String toString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return 42 + input;\r\n'"
	 * @generated
	 */
	int test(int input);

} // Article
