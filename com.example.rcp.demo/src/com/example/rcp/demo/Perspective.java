package com.example.rcp.demo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.example.rcp.demo.views.PlaygroundView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(PlaygroundView.ID, IPageLayout.LEFT, 0.95f,
				layout.getEditorArea());
		layout.setEditorAreaVisible(false);
	}
}
