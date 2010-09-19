package de.vogella.swtbot.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public void createPartControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("My Label");
		Text text = new Text(parent, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		text.setText("This is my text");

		// Define another text field but also assign an ID to this field for
		// SWTBot
		text = new Text(parent, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		text.setText("This text has an ID");
		text.setData("org.eclipse.swtbot.widget.key", "text1");

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}