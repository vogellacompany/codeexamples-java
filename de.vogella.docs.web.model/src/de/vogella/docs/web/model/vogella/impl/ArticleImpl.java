/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.vogella.docs.web.model.vogella.impl;

import de.vogella.docs.web.model.vogella.Article;
import de.vogella.docs.web.model.vogella.CodeExample;
import de.vogella.docs.web.model.vogella.vogellaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Article</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.vogella.docs.web.model.vogella.impl.ArticleImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.impl.ArticleImpl#getSort <em>Sort</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.impl.ArticleImpl#isRssRelevant <em>Rss Relevant</em>}</li>
 *   <li>{@link de.vogella.docs.web.model.vogella.impl.ArticleImpl#getCodeExamples <em>Code Examples</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArticleImpl extends EObjectImpl implements Article {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSort() <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSort()
	 * @generated
	 * @ordered
	 */
	protected static final int SORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSort() <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSort()
	 * @generated
	 * @ordered
	 */
	protected int sort = SORT_EDEFAULT;

	/**
	 * The default value of the '{@link #isRssRelevant() <em>Rss Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRssRelevant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RSS_RELEVANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRssRelevant() <em>Rss Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRssRelevant()
	 * @generated
	 * @ordered
	 */
	protected boolean rssRelevant = RSS_RELEVANT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCodeExamples() <em>Code Examples</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeExamples()
	 * @generated
	 * @ordered
	 */
	protected EList<CodeExample> codeExamples;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArticleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return vogellaPackage.Literals.ARTICLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, vogellaPackage.ARTICLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSort(int newSort) {
		int oldSort = sort;
		sort = newSort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, vogellaPackage.ARTICLE__SORT, oldSort, sort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRssRelevant() {
		return rssRelevant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRssRelevant(boolean newRssRelevant) {
		boolean oldRssRelevant = rssRelevant;
		rssRelevant = newRssRelevant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, vogellaPackage.ARTICLE__RSS_RELEVANT, oldRssRelevant, rssRelevant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CodeExample> getCodeExamples() {
		if (codeExamples == null) {
			codeExamples = new EObjectContainmentEList<CodeExample>(CodeExample.class, this, vogellaPackage.ARTICLE__CODE_EXAMPLES);
		}
		return codeExamples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case vogellaPackage.ARTICLE__CODE_EXAMPLES:
				return ((InternalEList<?>)getCodeExamples()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case vogellaPackage.ARTICLE__NAME:
				return getName();
			case vogellaPackage.ARTICLE__SORT:
				return getSort();
			case vogellaPackage.ARTICLE__RSS_RELEVANT:
				return isRssRelevant();
			case vogellaPackage.ARTICLE__CODE_EXAMPLES:
				return getCodeExamples();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case vogellaPackage.ARTICLE__NAME:
				setName((String)newValue);
				return;
			case vogellaPackage.ARTICLE__SORT:
				setSort((Integer)newValue);
				return;
			case vogellaPackage.ARTICLE__RSS_RELEVANT:
				setRssRelevant((Boolean)newValue);
				return;
			case vogellaPackage.ARTICLE__CODE_EXAMPLES:
				getCodeExamples().clear();
				getCodeExamples().addAll((Collection<? extends CodeExample>)newValue);
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
			case vogellaPackage.ARTICLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case vogellaPackage.ARTICLE__SORT:
				setSort(SORT_EDEFAULT);
				return;
			case vogellaPackage.ARTICLE__RSS_RELEVANT:
				setRssRelevant(RSS_RELEVANT_EDEFAULT);
				return;
			case vogellaPackage.ARTICLE__CODE_EXAMPLES:
				getCodeExamples().clear();
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
			case vogellaPackage.ARTICLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case vogellaPackage.ARTICLE__SORT:
				return sort != SORT_EDEFAULT;
			case vogellaPackage.ARTICLE__RSS_RELEVANT:
				return rssRelevant != RSS_RELEVANT_EDEFAULT;
			case vogellaPackage.ARTICLE__CODE_EXAMPLES:
				return codeExamples != null && !codeExamples.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", sort: ");
		result.append(sort);
		result.append(", rssRelevant: ");
		result.append(rssRelevant);
		result.append(')');
		return result.toString();
	}

} //ArticleImpl
