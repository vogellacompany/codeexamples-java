package de.vogella.preferences.page.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.preferences.page.Activator;

public class ShowPreferenceValues extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event)
				.getShell();
		String myPrefString = Activator.getDefault().getPreferenceStore()
				.getString("MySTRING1");
		MessageDialog.openInformation(shell, "Info", myPrefString);
		Boolean myPrefBoolean = Activator.getDefault().getPreferenceStore()
				.getBoolean("BOOLEAN_VALUE");
		// RadioGroupFieldEditor can get access
		String choice = Activator.getDefault().getPreferenceStore().getString("CHOICE");
		System.out.println(choice);
		MessageDialog.openInformation(shell, "Info", myPrefBoolean.toString());
		// I assume you get the rest by yourself
		return null;
	}

}
