package de.vogella.rcp.commands.parametersecond;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class NameValueParameter implements IParameterValues {

	@SuppressWarnings("unchecked")
	@Override
	public Map getParameterValues() {
		Map values =new HashMap();
		values.put("Jacki", "Jack the Ripper");
		values.put("Tim", "Tim Lot");
		return values;
	}

}
