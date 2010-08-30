package de.vogella.rcp.intro.editor;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import de.vogella.rcp.intro.editor.model.MyModel;
import de.vogella.rcp.intro.editor.provider.MyContentProvider;
import de.vogella.rcp.intro.editor.provider.MyLabelProvider;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.editor.view";

	private TableViewer viewer;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new MyContentProvider(viewer));
		viewer.setLabelProvider(new MyLabelProvider());
		viewer.setInput(new MyModel());
		getSite().setSelectionProvider(viewer);
		// New
		hookDoubleClickCommand();
	}

	// New
	private void hookDoubleClickCommand() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite()
						.getService(IHandlerService.class);
				try {
					handlerService.executeCommand(
							"de.vogella.rcp.intro.editor.callEditor", null);
				} catch (Exception ex) {
					throw new RuntimeException(
							"de.vogella.rcp.intro.editor.callEditor not found");
				}
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}