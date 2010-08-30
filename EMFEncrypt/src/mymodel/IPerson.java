package mymodel;

import org.eclipse.emf.ecore.EObject;
/**
 * @model
 */
public interface IPerson extends EObject {
	/**
	 * @model default="";
	 */
	public String getLastname();

	/**
	 * Sets the value of the '{@link mymodel.IPerson#getLastname <em>Lastname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastname</em>' attribute.
	 * @see #getLastname()
	 * @generated
	 */
	void setLastname(String value);
}
