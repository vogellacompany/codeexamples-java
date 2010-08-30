/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package extented.impl;

import base.impl.MyBaseClassImpl;

import extented.ExtentedPackage;
import extented.MyExtendedClass;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>My Extended Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link extented.impl.MyExtendedClassImpl#getDetailedField <em>Detailed Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MyExtendedClassImpl extends MyBaseClassImpl implements MyExtendedClass {
	/**
	 * The default value of the '{@link #getDetailedField() <em>Detailed Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailedField()
	 * @generated
	 * @ordered
	 */
	protected static final String DETAILED_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDetailedField() <em>Detailed Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailedField()
	 * @generated
	 * @ordered
	 */
	protected String detailedField = DETAILED_FIELD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MyExtendedClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtentedPackage.Literals.MY_EXTENDED_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDetailedField() {
		return detailedField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetailedField(String newDetailedField) {
		String oldDetailedField = detailedField;
		detailedField = newDetailedField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtentedPackage.MY_EXTENDED_CLASS__DETAILED_FIELD, oldDetailedField, detailedField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtentedPackage.MY_EXTENDED_CLASS__DETAILED_FIELD:
				return getDetailedField();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExtentedPackage.MY_EXTENDED_CLASS__DETAILED_FIELD:
				setDetailedField((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExtentedPackage.MY_EXTENDED_CLASS__DETAILED_FIELD:
				setDetailedField(DETAILED_FIELD_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExtentedPackage.MY_EXTENDED_CLASS__DETAILED_FIELD:
				return DETAILED_FIELD_EDEFAULT == null ? detailedField != null : !DETAILED_FIELD_EDEFAULT.equals(detailedField);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (detailedField: ");
		result.append(detailedField);
		result.append(')');
		return result.toString();
	}

} //MyExtendedClassImpl
