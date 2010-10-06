/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.databinding.emf.table.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see de.vogella.databinding.emf.table.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://model/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = de.vogella.databinding.emf.table.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.vogella.databinding.emf.table.model.impl.TodoImpl <em>Todo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.vogella.databinding.emf.table.model.impl.TodoImpl
	 * @see de.vogella.databinding.emf.table.model.impl.ModelPackageImpl#getTodo()
	 * @generated
	 */
	int TODO = 0;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__SUMMARY = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Todo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TODO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.vogella.databinding.emf.table.model.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.vogella.databinding.emf.table.model.impl.ModelImpl
	 * @see de.vogella.databinding.emf.table.model.impl.ModelPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 1;

	/**
	 * The feature id for the '<em><b>Todos</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TODOS = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link de.vogella.databinding.emf.table.model.Todo <em>Todo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Todo</em>'.
	 * @see de.vogella.databinding.emf.table.model.Todo
	 * @generated
	 */
	EClass getTodo();

	/**
	 * Returns the meta object for the attribute '{@link de.vogella.databinding.emf.table.model.Todo#getSummary <em>Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Summary</em>'.
	 * @see de.vogella.databinding.emf.table.model.Todo#getSummary()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Summary();

	/**
	 * Returns the meta object for the attribute '{@link de.vogella.databinding.emf.table.model.Todo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.vogella.databinding.emf.table.model.Todo#getDescription()
	 * @see #getTodo()
	 * @generated
	 */
	EAttribute getTodo_Description();

	/**
	 * Returns the meta object for class '{@link de.vogella.databinding.emf.table.model.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.vogella.databinding.emf.table.model.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the reference list '{@link de.vogella.databinding.emf.table.model.Model#getTodos <em>Todos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Todos</em>'.
	 * @see de.vogella.databinding.emf.table.model.Model#getTodos()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Todos();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link de.vogella.databinding.emf.table.model.impl.TodoImpl <em>Todo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.vogella.databinding.emf.table.model.impl.TodoImpl
		 * @see de.vogella.databinding.emf.table.model.impl.ModelPackageImpl#getTodo()
		 * @generated
		 */
		EClass TODO = eINSTANCE.getTodo();

		/**
		 * The meta object literal for the '<em><b>Summary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__SUMMARY = eINSTANCE.getTodo_Summary();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TODO__DESCRIPTION = eINSTANCE.getTodo_Description();

		/**
		 * The meta object literal for the '{@link de.vogella.databinding.emf.table.model.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.vogella.databinding.emf.table.model.impl.ModelImpl
		 * @see de.vogella.databinding.emf.table.model.impl.ModelPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Todos</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__TODOS = eINSTANCE.getModel_Todos();

	}

} //ModelPackage
