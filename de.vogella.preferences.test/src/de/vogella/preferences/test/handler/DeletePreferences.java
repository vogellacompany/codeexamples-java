package de.vogella.preferences.test.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

// Removes the preferences settings
public class DeletePreferences extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) {
		Preferences preferences = ConfigurationScope.INSTANCE
				.getNode("de.vogella.preferences.test");
		Preferences sub1 = preferences.node("note1");
		Preferences sub2 = preferences.node("node2");
		// Delete the existing settings
		try {
			sub1.clear();
			sub2.clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		// Forces the application to save the preferences
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
}
