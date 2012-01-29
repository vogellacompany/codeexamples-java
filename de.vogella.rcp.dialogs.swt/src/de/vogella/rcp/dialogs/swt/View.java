package de.vogella.rcp.dialogs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = "de.vogella.rcp.dialogs.swt.View"; //$NON-NLS-1$

	@Override
	public void createPartControl(Composite parent) {
		new MyComposite(parent, SWT.NONE);

	}

	@Override
	public void setFocus() {

	}

}
