package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MouseListenerExample {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		Button button = new Button(shell, SWT.READ_ONLY);
		button.setText("Press me");
		button.setSize(200, 200);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ((e.stateMask & SWT.CTRL) != 0) {
					System.out.println("SWT.CTRL");
				}
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
