package editortest;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import editortest.contentProvider.MyContentProvider;
import editortest.contentProvider.MyLabelProvider;

public class View extends ViewPart {

	public static final String ID = "EditorTest.view";
	private TableViewer viewer;

	public void createPartControl(Composite parent) {

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new MyContentProvider(viewer));
		viewer.setLabelProvider(new MyLabelProvider());
		viewer.setInput(getViewSite());
		// New
		hookDoubleClickCommand();
		getSite().setSelectionProvider(viewer);
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// New
	private void hookDoubleClickCommand() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite()
						.getService(IHandlerService.class);
				try {
					handlerService
							.executeCommand("editortest.callEditor", null);
				} catch (Exception ex) {
					throw new RuntimeException(
							"editortest.callEditor not found");
				}
			}
		});
	}

	// New
	public TableViewer getViewer() {
		return viewer;
	}
}