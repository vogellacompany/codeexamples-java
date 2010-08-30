package de.vogella.gwt.module.application.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.vogella.gwt.module.model.Person;

public class ModulTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Person p = new Person();
		p.setFirstName("Lars");
		Label label = new Label("Hello " + p.getFirstName());
		RootPanel.get().add(label);
	}
}
