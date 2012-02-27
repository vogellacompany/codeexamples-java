package de.vogella.rcp.undoredo;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.undoredo.view";

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		final IOperationHistory operationHistory = OperationHistoryFactory
				.getOperationHistory();
		final TextOperations operation = new TextOperations(getViewSite()
				.getShell());
		operationHistory.add(operation);
		Text text = new Text(parent, SWT.BORDER);
		Button button = new Button(parent, SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// IUndoableOperation operation = new TextOperations(View.this
				// .getViewSite().getShell());
				try {
					operationHistory.execute(operation, null, null);
				} catch (ExecutionException e1) {
				}
			}
		});

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	class TextOperations extends AbstractOperation {
		Shell shell;

		public TextOperations(Shell shell) {
			super("Readme Operation");
			this.shell = shell;
		}

		public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
			MessageDialog.openInformation(shell, "Readme_Editor",
					"View_Action_executed");
			return Status.OK_STATUS;
		}

		public IStatus undo(IProgressMonitor monitor, IAdaptable info) {
			MessageDialog.openInformation(shell, "Readme_Editor",
					"Undoing view action");
			return Status.OK_STATUS;
		}

		public IStatus redo(IProgressMonitor monitor, IAdaptable info) {
			MessageDialog.openInformation(shell, "Readme_Editor",
					"Redoing view action");
			return Status.OK_STATUS;
		}
	}

}