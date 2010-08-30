package de.vogella.rcp.intro.tooltip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.tooltip.view";

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		Text text = new Text(parent, SWT.NONE);
		text.setText("hello");
		text.setToolTipText("This is a tooltip");
	}

	@Override
	public void setFocus() {
		
	}

	
}