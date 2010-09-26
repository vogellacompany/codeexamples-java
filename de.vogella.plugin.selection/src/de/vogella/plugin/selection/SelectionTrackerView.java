package de.vogella.plugin.selection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class SelectionTrackerView extends ViewPart {


	@Override
	public void createPartControl(Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		ISelectionService selectionService = getViewSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				label.setText(selection.toString());
			}
		});
	}

	@Override
	public void setFocus() {

	}

}
