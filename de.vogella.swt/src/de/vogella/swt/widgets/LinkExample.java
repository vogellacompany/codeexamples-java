package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

public class LinkExample {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		Link link = new Link(shell, SWT.NONE);
		link.setText("<A>http://www.vogella.de</A>");
		Program.launch(link.getText());
		link.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
