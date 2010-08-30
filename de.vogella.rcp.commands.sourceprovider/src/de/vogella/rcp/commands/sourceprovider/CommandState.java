package de.vogella.rcp.commands.sourceprovider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class CommandState extends AbstractSourceProvider {
	public final static String MY_STATE = "de.vogella.rcp.commands.sourceprovider.active";
	public final static String ENABLED = "ENABLED";
	public final static String DISENABLED = "DISENABLED";
	private boolean enabled = true;


	@Override
	public void dispose() {
	}

	// We could return several values but for this example one value is sufficient
	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { MY_STATE };
	}
	
	// You cannot return NULL
	@SuppressWarnings("unchecked")
	@Override
	public Map getCurrentState() {
		Map map = new HashMap(1);
		String value = enabled ? ENABLED : DISENABLED;
		map.put(MY_STATE, value);
		return map;
	}

	

	// This method can be used from other commands to change the state
	// Most likely you would use a setter to define directly the state and not use this toogle method
	// But hey, this works well for my example
	public void toogleEnabled() {
		enabled = !enabled ;
		String value = enabled ? ENABLED : DISENABLED;
		fireSourceChanged(ISources.WORKBENCH, MY_STATE, value);
	}

}
