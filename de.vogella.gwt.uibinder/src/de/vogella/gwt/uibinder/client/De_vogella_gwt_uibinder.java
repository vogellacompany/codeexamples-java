package de.vogella.gwt.uibinder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class De_vogella_gwt_uibinder implements EntryPoint {
	
	public void onModuleLoad() {

		HelloWidgetWorld helloWorld =
		  new HelloWidgetWorld("able", "baker", "charlie");
        RootPanel.get().add(helloWorld);
        RootPanel.get().add(new MyHTMLTable());
    }

}
