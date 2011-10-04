package de.vogella.rcp.intro.workbench;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class View2 extends ViewPart {

	public View2() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Lars");
	}

	@Override
	public void setFocus() {

	}

}
