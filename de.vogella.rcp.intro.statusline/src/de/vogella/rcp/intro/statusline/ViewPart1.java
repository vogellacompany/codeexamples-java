package de.vogella.rcp.intro.statusline;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

public class ViewPart1 extends ViewPart {

	boolean pressed = false;

	@Override
	public void createPartControl(Composite parent) {
		Button setStatusLine = new Button(parent, SWT.PUSH);
		setStatusLine.setText("Set Statusline ");

		setStatusLine.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				String message = "I would like to say hello to you.";
				if (pressed) {
					message = "Thank you for using me";
				}
				setStatusLine(message);
				pressed = !pressed;
			}
		});
	}

	private void setStatusLine(String message) {
		// Get the status line and set the text
		IActionBars bars = getViewSite().getActionBars();
		bars.getStatusLineManager().setMessage(message);
	}

	@Override
	public void setFocus() {
	}

}
