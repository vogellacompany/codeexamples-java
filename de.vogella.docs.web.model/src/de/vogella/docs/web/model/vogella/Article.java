/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.docs.web.model.vogella;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Article</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.vogella.docs.web.model.vogella.Article#getName <em>Name</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.Article#getSort <em>Sort</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.Article#isRssRelevant <em>Rss Relevant</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.Article#getCodeExamples <em>Code Examples</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.vogella.docs.web.model.vogella.vogellaPackage#getArticle()
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
	 * @see de.vogella.docs.web.model.vogella.vogellaPackage#getArticle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.vogella.docs.web.model.vogella.Article#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Sort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sort</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sort</em>' attribute.
	 * @see #setSort(int)
	 * @see de.vogella.docs.web.model.vogella.vogellaPackage#getArticle_Sort()
	 * @model
	 * @generated
	 */
	int getSort();

	/**
	 * Sets the value of the '{@link de.vogella.docs.web.model.vogella.Article#getSort <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sort</em>' attribute.
	 * @see #getSort()
	 * @generated
	 */
	void setSort(int value);

	/**
	 * Returns the value of the '<em><b>Rss Relevant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rss Relevant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rss Relevant</em>' attribute.
	 * @see #setRssRelevant(boolean)
	 * @see de.vogella.docs.web.model.vogella.vogellaPackage#getArticle_RssRelevant()
	 * @model
	 * @generated
	 */
	boolean isRssRelevant();

	/**
	 * Sets the value of the '{@link de.vogella.docs.web.model.vogella.Article#isRssRelevant <em>Rss Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rss Relevant</em>' attribute.
	 * @see #isRssRelevant()
	 * @generated
	 */
	void setRssRelevant(boolean value);

	/**
	 * Returns the value of the '<em><b>Code Examples</b></em>' containment reference list.
	 * The list contents are of type {@link de.vogella.docs.web.model.vogella.CodeExample}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code Examples</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code Examples</em>' containment reference list.
	 * @see de.vogella.docs.web.model.vogella.vogellaPackage#getArticle_CodeExamples()
	 * @model containment="true"
	 * @generated
	 */
	EList<CodeExample> getCodeExamples();

} // Article
