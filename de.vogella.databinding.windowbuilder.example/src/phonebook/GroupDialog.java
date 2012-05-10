package phonebook;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import phonebook.model.PhoneGroup;

public class GroupDialog extends Dialog {

	private Text text;
	private final PhoneGroup group;
	private final boolean canCreate;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 */
	public GroupDialog(Shell parentShell, PhoneGroup group, boolean canCreate) {
		super(parentShell);
		this.group = group;
		this.canCreate = canCreate;
	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);

		final Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setText("Name:");

		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		//
		return container;
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		if (canCreate) {
			createButton(parent, IDialogConstants.CANCEL_ID,
					IDialogConstants.CANCEL_LABEL, false);
		}
		initDataBindings();
	}

	/**
	 * Return the initial size of the dialog
	 */
	protected Point getInitialSize() {
		return new Point(343, 110);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Phone Group");
	}

	protected DataBindingContext initDataBindings() {
		IObservableValue groupNameObserveValue = BeansObservables.observeValue(
				group, "name");
		IObservableValue textTextObserveWidget = SWTObservables.observeText(
				text, SWT.Modify);
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(textTextObserveWidget, groupNameObserveValue,
				null, null);
		//
		return bindingContext;
	}

}