package de.vogella.rcp.fixedperspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		// works
		layout.setFixed(true);
		layout.addView("de.vogella.rcp.fixedperspective.view",
				IPageLayout.LEFT, 0.5f, layout.getEditorArea());
		layout.addView("de.vogella.rcp.fixedperspective.view2",
				IPageLayout.RIGHT, 0.5f, layout.getEditorArea());

		// does not work
		// layout.addView("de.vogella.rcp.fixedperspective.view",
		// IPageLayout.LEFT, 0.5f, layout.getEditorArea());
		// layout.addView("de.vogella.rcp.fixedperspective.view2",
		// IPageLayout.RIGHT, 0.5f, layout.getEditorArea());
		// layout.setFixed(false);

	}

}
