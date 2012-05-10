package convert;

import java.util.Currency;
import java.util.Locale;

/**
 * @author lobas_av
 *
 */
public class Cash extends AbstractModelObject {
	private int m_value;
	private Currency m_currency;
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	public Cash() {
		m_value = 1000000;
		m_currency = Currency.getInstance(Locale.getDefault());
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Properties
	//
	////////////////////////////////////////////////////////////////////////////
	public int getValue() {
		return m_value;
	}
	public void setValue(int value) {
		int oldValue = m_value;
		m_value = value;
		firePropertyChange("value", oldValue, m_value);
	}
	public Currency getCurrency() {
		return m_currency;
	}
	public void setCurrency(Currency currency) {
		Object oldValue = m_currency;
		m_currency = currency;
		firePropertyChange("currency", oldValue, m_currency);
	}
}