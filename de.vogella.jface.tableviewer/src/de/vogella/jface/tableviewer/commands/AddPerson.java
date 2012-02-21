package de.vogella.jface.tableviewer.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.vogella.jface.tableviewer.View;
import de.vogella.jface.tableviewer.dialogs.AddPersonDialog;
import de.vogella.jface.tableviewer.model.ModelProvider;

public class AddPerson extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ModelProvider persons = ModelProvider.INSTANCE;
		AddPersonDialog dialog = new AddPersonDialog(window.getShell());
		dialog.open();
		if (dialog.getPerson() != null) {
			persons.getPersons().add(dialog.getPerson());
			// Updating the display in the view
			IWorkbenchPage page = window.getActivePage();
			View view = (View) page.findView(View.ID);
			view.refresh();
		}
		return null;
	}
}
