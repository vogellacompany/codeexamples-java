package de.vogella.rcp.intro.dialogs.standard.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenDialog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
			System.out.println("moin");
			Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

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

			// Directly standard selection as modular 
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

			// Now a few messages
			MessageDialog.openConfirm(shell, "Confirm", "Please confirm");
			MessageDialog.openError(shell, "Error", "Error occured");
			MessageDialog.openInformation(shell, "Info", "Info for you");
			MessageDialog.openQuestion(shell, "Question", "Really, really?");
			MessageDialog.openWarning(shell, "Warning", "I warn you");

			return null;
		}


}
