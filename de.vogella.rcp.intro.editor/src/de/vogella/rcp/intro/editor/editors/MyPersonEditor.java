package de.vogella.rcp.intro.editor.editors;

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

import de.vogella.rcp.intro.editor.model.Person;

public class MyPersonEditor extends EditorPart {
	public static final String ID = "de.vogella.rcp.intro.editor.editors.MyPersonEditor";
	private Person person;
	private Text text2;

	public MyPersonEditor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		person.getAddress().setCountry(text2.getText());
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		person = ((MyPersonEditorInput) input).getPerson();
		setPartName(person.getFirstName());
		setTitleToolTip("This is my new tooltip");
		setContentDescription("This is my new tooltip");

	}

	@Override
	public String getTitleToolTip() {
		return ("This is my new tooltip");
	}

	@Override
	public boolean isDirty() {
		if (person.getAddress().getCountry().equals(text2.getText())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.BORDER);
		label1.setText("Person: ");
		Label personName = new Label(parent, SWT.BORDER);
		personName.setText(person.toString());
		Label label2 = new Label(parent, SWT.BORDER);
		label2.setText("Country");
		text2 = new Text(parent, SWT.BORDER);
		text2.setText(person.getAddress().getCountry());
	}

	@Override
	public void setFocus() {
	}

}
