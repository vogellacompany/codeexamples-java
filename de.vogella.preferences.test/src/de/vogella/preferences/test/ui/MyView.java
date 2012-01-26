package de.vogella.preferences.test.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MyView extends ViewPart {

	public static final String ID = "de.vogella.preferences.test.view";

	@Override
	public void createPartControl(Composite parent) {
		ButtonComposite composite = new ButtonComposite(parent, SWT.NONE);
	}

	@Override
	public void setFocus() {

	}

}
