package convert;

import java.util.Currency;

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * @author lobas_av
 *
 */
public class ConverterStringToCurrency implements IConverter {
	////////////////////////////////////////////////////////////////////////////
	//
	// IConverter
	//
	////////////////////////////////////////////////////////////////////////////
	public Object getFromType() {
		return String.class;
	}
	public Object getToType() {
		return Currency.class;
	}
	public Object convert(Object fromObject) {
		try {
			String value = (String) fromObject;
			return Currency.getInstance(value.toUpperCase());
		} catch (Throwable e) {
			return null;
		}
	}
}