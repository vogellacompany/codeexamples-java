package de.vogella.rcp.intro.workbench;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		configurer.setTitle("RCP Application");

	}

	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();

		// Register a part listener for a certain View / Part

		page.addPartListener(new IPartListener2() {
			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
				System.out.println("Part visible: " + partRef.getId());
			}

			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
				System.out.println("Part opened: " + partRef.getId());
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
				System.out.println("Part hidden: " + partRef.getId());
			}

			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {
				System.out.println("Part deactivated:" + partRef.getId());
			}

			@Override
			public void partClosed(IWorkbenchPartReference partRef) {
				System.out.println("Part closed:" + partRef.getId());
			}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				System.out.println("Part top:" + partRef.getId());
			}

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				System.out.println("Part activated: " + partRef.getId());
			}
		});
	}
}
