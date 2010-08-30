package de.vogella.jface.tableviewer.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.vogella.jface.tableviewer.View;
import de.vogella.jface.tableviewer.model.Person;

public class CopyPersonClipboard extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IViewPart view = page.findView(View.ID);
		Clipboard cb = new Clipboard(Display.getDefault());
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		List<Person> personList = new ArrayList<Person>();
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			for (Iterator<Person> iterator = sel.iterator(); iterator.hasNext();) {
				Person person = iterator.next();
				personList.add(person);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Person person : personList) {
			sb.append(personToString(person));
		}
		TextTransfer textTransfer = TextTransfer.getInstance();
		cb.setContents(new Object[] { sb.toString() },
				new Transfer[] { textTransfer });

		return null;
	}

	private String personToString(Person person) {
		return person.getFirstName() + "\t" + person.getLastName() + "\t"
				+ person.getGender() + "\t" + person.isMarried()
				+ System.getProperty("line.separator");
	}

}
