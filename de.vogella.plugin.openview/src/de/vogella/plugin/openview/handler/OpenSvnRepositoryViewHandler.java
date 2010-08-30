package de.vogella.plugin.openview.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenSvnRepositoryViewHandler extends AbstractHandler {

	// This requires that the subversion plugins are installed
	private static final String VIEWID = "org.eclipse.team.svn.ui.repository.RepositoriesView";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(VIEWID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;

	}
}
