package de.vogella.databinding.example.converter;

import org.eclipse.core.databinding.conversion.Converter;

public class CommaSeparatedStringToStringArrayConverter extends Converter {

	public CommaSeparatedStringToStringArrayConverter() {
		super(String.class, String[].class);
	}

	@Override
	public Object convert(Object fromObject) {
		if(fromObject instanceof String){
			return ((String) fromObject).split(";");
		}
		return null;
	}

}
