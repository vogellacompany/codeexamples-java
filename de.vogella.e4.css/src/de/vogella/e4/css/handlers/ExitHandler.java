
package de.vogella.e4.css.handlers;

import org.eclipse.e4.workbench.ui.IWorkbench;

public class ExitHandler {
	public void execute(IWorkbench workbench) {
		workbench.close();
	}
}
