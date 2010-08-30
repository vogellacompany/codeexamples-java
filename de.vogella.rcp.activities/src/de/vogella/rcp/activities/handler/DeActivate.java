package de.vogella.rcp.activities.handler;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.handlers.HandlerUtil;

public class DeActivate extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchActivitySupport activitySupport = HandlerUtil
				.getActiveWorkbenchWindow(event).getWorkbench()
				.getActivitySupport();
		Set<String> enabledActivities = new HashSet<String>();
		activitySupport.setEnabledActivityIds(enabledActivities);
		// Now I have to reset the perspective to update also the views
		HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().resetPerspective();
		return null;
	}

}
