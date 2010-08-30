package de.vogella.e4.xwt;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class XwtComponent1 extends Composite {

	public XwtComponent1(Composite parent, int style) {
		super(parent, style);
	}

	protected void selection(Event event) {
		Button button = (Button) event.widget;
		button.setText("Ok");
	}
}
