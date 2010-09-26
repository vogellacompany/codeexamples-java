package de.vogella.rcp.editor.example.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import de.vogella.rcp.editor.example.model.MyModel;
import de.vogella.rcp.editor.example.model.Person;

public class MyPersonEditor extends EditorPart {
	public static final String ID = "de.vogella.rcp.editor.example.editor.personeditor";
	private Person person;
	private MyPersonEditorInput input;

	// Will be called before createPartControl
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof MyPersonEditorInput)) {
			throw new RuntimeException("Wrong input");
		}

		MyPersonEditorInput new_name = (MyPersonEditorInput) input;
		this.input = (MyPersonEditorInput) input;
		setSite(site);
		setInput(input);
		person = MyModel.getInstance().getPersonById(this.input.getId());
		setPartName("Person " + person.getLastName());
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.BORDER);
		label1.setText("First Name");
		Text text = new Text(parent, SWT.BORDER);
		text.setText(person.getFirstName());
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// person.getAddress().setCountry(text2.getText());
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
	}

}
