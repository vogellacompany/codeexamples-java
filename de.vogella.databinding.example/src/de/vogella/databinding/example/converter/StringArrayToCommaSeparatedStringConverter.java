package de.vogella.databinding.example.converter;

import org.eclipse.core.databinding.conversion.Converter;

public class StringArrayToCommaSeparatedStringConverter extends Converter {

	public StringArrayToCommaSeparatedStringConverter() {
		super(String[].class, String.class);
	}

	@Override
	public Object convert(Object fromObject) {
		if(fromObject instanceof String[]) {
			String[] stringArray = (String[]) fromObject;
			StringBuilder sb = new StringBuilder();
			int length = stringArray.length;
			for (int i = 0; i < length; i++) {
				String string = stringArray[i];
				sb.append(string);
				if(i + 1 < length) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		return null;
	}
}
