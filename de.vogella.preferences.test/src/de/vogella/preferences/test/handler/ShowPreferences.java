package de.vogella.preferences.test.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.service.prefs.Preferences;

import de.vogella.preferences.test.Activator;

// Shows the current preferences
public class ShowPreferences extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event)
				.getShell();
		Preferences preferences = ConfigurationScope.INSTANCE
				.getNode("de.vogella.preferences.test");
		IPreferenceStore preferenceStore = Activator.getDefault()
				.getPreferenceStore();

		preferenceStore.setValue("key1", true);
		preferenceStore.getBoolean("key1");

		Preferences sub1 = preferences.node("note1");
		Preferences sub2 = preferences.node("node2");
		String string = sub1.get("h1", "default");
		MessageDialog.openInformation(shell, "Info", string);
		MessageDialog.openInformation(shell, "Info", sub1.get("h2", "default"));
		MessageDialog.openInformation(shell, "Info", sub2.get("h1", "default"));
		return null;
	}
}
