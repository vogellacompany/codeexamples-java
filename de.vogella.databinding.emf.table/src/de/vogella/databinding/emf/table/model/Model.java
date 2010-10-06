/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.databinding.emf.table.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.vogella.databinding.emf.table.model.Model#getTodos <em>Todos</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.vogella.databinding.emf.table.model.ModelPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Todos</b></em>' reference list.
	 * The list contents are of type {@link de.vogella.databinding.emf.table.model.Todo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Todos</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Todos</em>' reference list.
	 * @see de.vogella.databinding.emf.table.model.ModelPackage#getModel_Todos()
	 * @model
	 * @generated
	 */
	EList<Todo> getTodos();

} // Model
