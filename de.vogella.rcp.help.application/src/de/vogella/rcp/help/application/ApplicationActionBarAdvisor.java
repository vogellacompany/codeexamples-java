package de.vogella.rcp.help.application;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	protected void makeActions(IWorkbenchWindow window) {
		register(ActionFactory.HELP_SEARCH.create(window));
		register(ActionFactory.DYNAMIC_HELP.create(window));
	}

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

}
