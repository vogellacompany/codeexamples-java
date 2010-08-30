package model;

import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */
public interface IPerson extends EObject {
	/**
	 * @model
	 */
	public String getFirstName();

	void setFirstName(String value);
}
