package de.vogella.jobs.first.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	// If you run this in Eclipse 4.0 un-comment this to get the shell injected
	// @Inject Shell shell

	@Override
	public void createPartControl(Composite parent) {
		// Use in Eclipse 3.x to get the shell
		// Comment the next line out in Eclipse 4.x
		final Shell shell = getViewSite().getShell();

		Button button = new Button(parent, SWT.PUSH);
		button.addSelectionListener(new MySelectionAdapter(shell));
	}

	@Override
	public void setFocus() {

	}

}
