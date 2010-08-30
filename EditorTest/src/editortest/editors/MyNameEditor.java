package editortest.editors;

import mydomain.MyModel.Person;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import editortest.View;

public class MyNameEditor extends EditorPart implements ISelectionListener {
	public static final String ID = "editortest.editors.MyNameEditor";
	private Text text1;
	private Text text2;
	private Person person;
	private boolean changed = false;

	public MyNameEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		person.setFirstName(text1.getText());
		person.setLastName(text2.getText());
		changed = false;
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());

	}

	@Override
	public boolean isDirty() {
		return changed;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(View.ID,
				(ISelectionListener) this);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.BORDER);
		label1.setText("First Name");
		text1 = new Text(parent, SWT.BORDER);
		Label label2 = new Label(parent, SWT.BORDER);
		label2.setText("Last Name");
		text2 = new Text(parent, SWT.BORDER);
		text2.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				changed = true;
			}

		});
		text1.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				changed = true;
			}

		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Person newPerson = (Person) ((IStructuredSelection) selection)
					.getFirstElement();
			if (person == null) {
				person = newPerson;
				text1.setText(person.getFirstName());
				text2.setText(person.getLastName());
				changed = false;
			}

		}
	}

	@Override
	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(View.ID,
				(ISelectionListener) this);
	}
}
