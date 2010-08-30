package de.vogella.gwt.uibinder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MyHTMLTable extends Composite {
	// Annotation can be used to change the name of the associated xml file
	// @UiTemplate("HelloWidgetWorld.ui.xml")
	interface MyUiBinder extends UiBinder<Widget, MyHTMLTable> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);


	public MyHTMLTable(String... names) {
		// sets listBox
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}
