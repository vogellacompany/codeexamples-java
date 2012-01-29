package de.vogella.rcp.intro.wizards;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.vogella.rcp.intro.wizards.views.MyView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(MyView.ID, IPageLayout.RIGHT, 0.95f,
				layout.getEditorArea());
	}
}
