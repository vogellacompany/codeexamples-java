package de.vogella.birt.stocks.rcp;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.birt.report.viewer.utilities.WebViewer;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class View extends ViewPart {
	public static final String ID = "de.vogella.birt.stocks.rcp.view";

	public void createPartControl(Composite parent) {
		String path = "";

        try {
                Bundle bundle = Platform.getBundle("de.vogella.birt.stocks.rcp");
                path = new URL(bundle.getLocation()).getPath();
                path = path .substring(6); //"file:/" 0 bis 5
                path = path .replace("/", "\\");
                path = path + "stock_report_rcp.rptdesign";
        }
        catch (MalformedURLException me){
                System.out.println(me.getStackTrace());
        }

        Browser browser = new Browser(parent, SWT.NONE);
        // Use the filename of your report
        WebViewer.display(path, WebViewer.HTML, browser, "frameset");	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}
