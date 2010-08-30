package de.vogella.mylyn.tasksmodify.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.context.core.ContextCore;
import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.internal.context.core.ContextCorePlugin;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;
import org.eclipse.ui.handlers.HandlerUtil;

public class SampleHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		if (selection ==null){
			return null;
		}
		
		Object firstElement = selection.getFirstElement();
		
		if (firstElement instanceof IPackageFragment) {
			IPackageFragment mypackage = (IPackageFragment) firstElement;
			try {
				if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE){
					 ICompilationUnit[] compilationUnits = mypackage.getCompilationUnits();
					 for (ICompilationUnit iCompilationUnit : compilationUnits) {
						 IInteractionContext activeContext = ContextCore.getContextManager()
							.getActiveContext();
						 ContextCorePlugin.getContextManager().processInteractionEvent(iCompilationUnit,
									Kind.PROPAGATION, InteractionEvent.ID_UNKNOWN, activeContext);
					}
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
