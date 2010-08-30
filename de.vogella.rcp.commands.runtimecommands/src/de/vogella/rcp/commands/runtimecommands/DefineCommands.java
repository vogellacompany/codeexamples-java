package de.vogella.rcp.commands.runtimecommands;

import org.eclipse.swt.SWT;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class DefineCommands extends ExtensionContributionFactory {

	@Override
	public void createContributionItems(IServiceLocator serviceLocator,
			IContributionRoot additions) {
		CommandContributionItemParameter p = new CommandContributionItemParameter(
				serviceLocator, "",
				"org.eclipse.ui.file.exit",
				SWT.PUSH);
		p.label = "Exit the application";
		p.icon = Activator.getImageDescriptor("icons/alt_window_16.gif");

		CommandContributionItem item = new CommandContributionItem(p);
		item.setVisible(true);
		additions.addContributionItem(item, null);
	}

}
