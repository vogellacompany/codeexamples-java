package com.example.rcp.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

public class WorkbenchWindowControlContribution1 extends
		WorkbenchWindowControlContribution {

	public WorkbenchWindowControlContribution1() {
	}

	public WorkbenchWindowControlContribution1(String id) {
		super(id);
	}

	@Override
	protected Control createControl(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);
		return text;
	}

}
