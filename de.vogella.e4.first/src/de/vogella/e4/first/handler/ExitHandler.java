
package de.vogella.e4.first.handler;

import org.eclipse.e4.ui.workbench.IWorkbench;

public class ExitHandler {
	public void execute(IWorkbench workbench) {
		workbench.close();
	}
}
