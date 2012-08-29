package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DateTimeExample {

	public static void main(String[] args) {

		// setup the SWT window
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());

		// initialize a parent composite with a grid layout manager
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);
		DateTime calender = new DateTime(parent, SWT.CALENDAR);
		DateTime date = new DateTime(parent, SWT.DATE);
		DateTime time = new DateTime(parent, SWT.TIME);
		// Date Selection as a drop-down
		DateTime dateD = new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		// tear down the SWT window
		display.dispose();
	}

}
