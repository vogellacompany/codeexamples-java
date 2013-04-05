package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LabelAndTextSWT {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		// Layout manager handle the layout
		// of the widgets in the container
		shell.setLayout(new FillLayout());

		Label label = new Label(shell, SWT.BORDER);
		label.setText("This is a label");
		label.setToolTipText("This is the tooltip of this label");
		Text text = new Text(shell, SWT.BORDER);
		text.setText("This is the text in the text widget");
		text.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
