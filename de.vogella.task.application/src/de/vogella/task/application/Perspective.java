package de.vogella.task.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.vogella.task.application.views.TaskOverview;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);

		layout.addView(TaskOverview.ID, IPageLayout.TOP, 0.6f, editorArea);
	}

}
