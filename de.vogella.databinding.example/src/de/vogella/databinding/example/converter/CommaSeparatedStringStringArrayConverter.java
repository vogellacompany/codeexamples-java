package de.vogella.databinding.example.converter;

import org.eclipse.core.databinding.conversion.Converter;

public class CommaSeparatedStringStringArrayConverter extends Converter {

	public CommaSeparatedStringStringArrayConverter() {
		// pass null for undefined fromType and toType
		super(null, null);
	}

	@Override
	public Object convert(Object fromObject) {
		if (fromObject instanceof String) {
			return ((String) fromObject).split(";");
		} else if (fromObject instanceof String[]) {
			String[] stringArray = (String[]) fromObject;
			StringBuilder sb = new StringBuilder();
			int length = stringArray.length;
			for (int i = 0; i < length; i++) {
				String string = stringArray[i];
				sb.append(string);
				if (i + 1 < length) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		throw new IllegalArgumentException(fromObject.getClass() + " type cannot be converted by " + getClass());
	}

}
