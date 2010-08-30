package de.vogella.swtbrowser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = "de.vogella.swtbrowser.view";
	
	public void createPartControl(Composite parent) {
		final Browser b = new Browser(parent, SWT.MOZILLA);
		b.setUrl("www.vogella.de");
		b.addProgressListener(new ProgressListener() {
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("Page loaded");
				System.out.println(b.execute("alert(\"1\");"));
			}
			@Override
			public void changed(ProgressEvent event) {
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}