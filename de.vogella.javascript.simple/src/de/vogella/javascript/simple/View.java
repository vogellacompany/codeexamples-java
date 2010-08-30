package de.vogella.javascript.simple;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.javascript.simple.view";


	public void createPartControl(Composite parent) {
		// You need XULRunner installed to use this line
//		final Browser b = new Browser(parent, SWT.MOZILLA);
		final Browser b = new Browser(parent, SWT.NONE); // Uses IE on MS Windows
		b.setUrl("http://www.vogella.de");
		b.addProgressListener(new ProgressListener() {
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("Page loaded");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Execute JavaScript in the browser
				b.execute("alert(\"JavaScript, called from Java\");"); 
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