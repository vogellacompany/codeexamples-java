package de.vogella.rap.first;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = "de.vogella.rap.first.View";

	@Override
	public void createPartControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("This is my label");
		Text text = new Text(parent, SWT.NONE);
		text.setText("Input");
	}

	@Override
	public void setFocus() {

	}

}
