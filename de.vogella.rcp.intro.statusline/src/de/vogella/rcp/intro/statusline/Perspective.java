package de.vogella.rcp.intro.statusline;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView("de.vogella.rcp.intro.statusline.View1", true, IPageLayout.LEFT, 1.0f, null);
	}
}
