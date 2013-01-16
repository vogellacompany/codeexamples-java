package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TabExample {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("Button1");
		Button b2 = new Button(shell, SWT.PUSH);
		b2.setText("Button2");
		Button b3 = new Button(shell, SWT.PUSH);
		b3.setText("Button3");

		Control[] controls = new Control[] { b2, b1, b3 };
		shell.setTabList(controls);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
