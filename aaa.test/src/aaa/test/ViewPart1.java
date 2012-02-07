package aaa.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ViewPart1 extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Hello");
		Button button = new Button(parent, SWT.PUSH);
		button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	@Override
	public void setFocus() {

	}

}
