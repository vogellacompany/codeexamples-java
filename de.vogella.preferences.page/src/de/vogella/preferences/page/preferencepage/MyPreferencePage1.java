package de.vogella.preferences.page.preferencepage;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.vogella.preferences.page.Activator;

public class MyPreferencePage1 extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private StringFieldEditor stringField1;

	public MyPreferencePage1() {
		super(GRID);
	}

	public void createFieldEditors() {
		addField(new DirectoryFieldEditor("PATH", "&Directory preference:",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor("BOOLEAN_VALUE",
				"&An example of a boolean preference", getFieldEditorParent()));

		addField(new RadioGroupFieldEditor("CHOICE",
				"An example of a multiple-choice preference", 1,
				new String[][] { { "&Choice 1", "choice1" },
						{ "C&hoice 2", "choice2" } }, getFieldEditorParent()));
		stringField1 = new StringFieldEditor("MySTRING1",
				"A &text preference:", getFieldEditorParent());
		addField(stringField1);
		addField(new StringFieldEditor("MySTRING2", "A &text preference:",
				getFieldEditorParent()));
	}



	// checkState allow you to perform validations
	@Override
	protected void checkState() {
		super.checkState();
		if (stringField1.getStringValue() != null
				&& stringField1.getStringValue().length() > 0) {
			setErrorMessage(null);
			setValid(true);
		} else {
			setErrorMessage("First text field must be maintained");
			setValid(false);
		}
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("A demonstration of a preference page implementation");
	}
}
