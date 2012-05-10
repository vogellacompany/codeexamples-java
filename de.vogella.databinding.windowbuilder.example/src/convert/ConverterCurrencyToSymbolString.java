package convert;

import java.util.Currency;

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * @author lobas_av
 *
 */
public class ConverterCurrencyToSymbolString implements IConverter {
	////////////////////////////////////////////////////////////////////////////
	//
	// IConverter
	//
	////////////////////////////////////////////////////////////////////////////
	public Object getFromType() {
		return Currency.class;
	}
	public Object getToType() {
		return String.class;
	}
	public Object convert(Object fromObject) {
		if (fromObject == null) {
			return "???";
		}
		Currency currency = (Currency) fromObject;
		return currency.getSymbol();
	}
}