package editortest.handler;

import mydomain.MyModel.Person;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import editortest.View;
import editortest.editors.MyNameEditor;
import editortest.editors.MyNameEditorInput;

public class CallEditor extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		View view = (View) page.findView(View.ID);
		TableViewer viewer = view.getViewer();
		// Get the selection
		ISelection selection = viewer.getSelection();
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			// If we had a selection lets open the editor
			if (obj != null) {
				Person person = (Person) obj;
				MyNameEditorInput input = new MyNameEditorInput(person
						.getLastName());
				try {
					page.openEditor(input, MyNameEditor.ID);
					viewer.setSelection(viewer.getSelection());
				} catch (PartInitException e) {
				}
			}
		}
		return null;
	}

}
