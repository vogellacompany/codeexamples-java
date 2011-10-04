package de.vogella.preferences.test.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

// Saves some preferences
public class SetPreferences extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Preferences preferences = ConfigurationScope.INSTANCE
				.getNode("de.vogella.preferences.test");
		Preferences sub1 = preferences.node("note1");
		Preferences sub2 = preferences.node("node2");
		sub1.put("h1", "Hello");
		sub1.put("h2", "Hello again");
		sub2.put("h1", "Moin");

		try {
			// Forces the application to save the preferences
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}
