package de.vogella.rcp.dialogs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MyComposite extends Composite {

	public MyComposite(final Composite parent, int style) {
		super(parent, style);
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Press me");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = parent.getShell();
				openDialogs(shell);
			}
		});

	}

	private void openDialogs(Shell shell) {
		// File standard dialog
		FileDialog fileDialog = new FileDialog(shell);
		// Set the text
		fileDialog.setText("Select File");
		// Set filter on .txt files
		fileDialog.setFilterExtensions(new String[] { "*.txt" });
		// Put in a readable name for the filter
		fileDialog.setFilterNames(new String[] { "Textfiles(*.txt)" });
		// Open Dialog and save result of selection
		String selected = fileDialog.open();
		System.out.println(selected);

		// Directly standard selection
		DirectoryDialog dirDialog = new DirectoryDialog(shell);
		dirDialog.setText("Select your home directory");
		String selectedDir = dirDialog.open();
		System.out.println(selectedDir);

		// Select Font
		FontDialog fontDialog = new FontDialog(shell);
		fontDialog.setText("Select your favorite font");
		FontData selectedFont = fontDialog.open();
		System.out.println(selectedFont);

		// Select Color
		ColorDialog colorDialog = new ColorDialog(shell);
		colorDialog.setText("Select your favorite color");
		RGB selectedColor = colorDialog.open();
		System.out.println(selectedColor);

		// Message
		MessageBox messageDialog = new MessageBox(shell, SWT.ERROR);
		messageDialog.setText("Evil Error has happend");
		messageDialog.setMessage("This is fatal!!!");
		int returnCode = messageDialog.open();
		System.out.println(returnCode);

		// Message with ok and cancel button and info icon
		messageDialog = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK
				| SWT.CANCEL);
		messageDialog.setText("My info");
		messageDialog.setMessage("Do you really want to do this.");
		returnCode = messageDialog.open();
		System.out.println(returnCode);
	}

}
