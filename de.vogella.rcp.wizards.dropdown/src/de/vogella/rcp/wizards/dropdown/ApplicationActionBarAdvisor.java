package de.vogella.rcp.wizards.dropdown;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private final IActionBarConfigurer configurer;
	private IWorkbenchAction wizard;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		this.configurer = configurer;
	}

	protected void makeActions(IWorkbenchWindow window) {
		wizard = ActionFactory.NEW_WIZARD_DROP_DOWN
				.create(window);
		register(wizard);
	}

	protected void fillMenuBar(IMenuManager menuBar) {

	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		coolBar.add(wizard);
		super.fillCoolBar(coolBar);
	}
}
