package de.vogella.gwt.helloserver.client.entrypoint;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.vogella.gwt.helloserver.client.service.MyUserCallback;
import de.vogella.gwt.helloserver.client.service.MyUserService;
import de.vogella.gwt.helloserver.client.service.MyUserServiceAsync;
import de.vogella.gwt.helloserver.client.table.MyTable;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloServer implements EntryPoint {

	private MyTable table;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		table = new MyTable(null);

		Button button = new Button("Click me");

		// We can add style names
		button.addStyleName("pc-template-btn");
		// or we can set an id on a specific element for styling

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("100%");
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		vPanel.add(button);

		vPanel.add(table);

		// Add table and button to the RootPanel
		RootPanel.get().add(vPanel);

		// Create the dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Welcome to GWT Server Communication!");
		dialogBox.setAnimationEnabled(true);
		Button closeButton = new Button("close");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setWidth("100%");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogVPanel.add(closeButton);

		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		// Set the contents of the Widget
		dialogBox.setWidget(dialogVPanel);

		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MyUserServiceAsync service = (MyUserServiceAsync) GWT
						.create(MyUserService.class);
				MyUserCallback myUserCallback = new MyUserCallback(table);
				service.getUserList(myUserCallback);
			}
		});

	}
}
