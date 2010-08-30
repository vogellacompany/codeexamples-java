package de.vogella.rcp.intro.fade;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.UIPlugin;

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
	public void postWindowClose() {
		Shell shell = UIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getShell();
		while (shell.getAlpha() > 0) {
			int alphaValue = shell.getAlpha() - 8;
			int newAlpha = alphaValue > 0 ? alphaValue : 0;
			shell.setAlpha(newAlpha);
			// Consider evil non-alpha systems
			if (shell.getAlpha()== 255){
				break; 
			}
			wait(50);
			shell.update();

		}
		super.postWindowClose();
	}

	public static void wait(int n) {
		long t0, t1;
		t0 = System.currentTimeMillis();
		do {
			t1 = System.currentTimeMillis();
		} while (t1 - t0 < n);
	}
}
