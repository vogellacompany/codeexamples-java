package de.vogella.databinding.person.swt;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(View.ID, IPageLayout.TOP, IPageLayout.RATIO_MAX,
				IPageLayout.ID_EDITOR_AREA);
		layout.setEditorAreaVisible(false);
	}
}
