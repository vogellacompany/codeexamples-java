package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ComboTest {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setItems(new String[] { "Linux", "Android", "Java" });
		combo.select(0);
		combo.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
