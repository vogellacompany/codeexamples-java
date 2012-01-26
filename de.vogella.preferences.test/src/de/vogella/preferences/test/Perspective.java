package de.vogella.preferences.test;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.vogella.preferences.test.ui.MyView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(MyView.ID, IPageLayout.LEFT, 0.95f,
				layout.getEditorArea());
	}
}
