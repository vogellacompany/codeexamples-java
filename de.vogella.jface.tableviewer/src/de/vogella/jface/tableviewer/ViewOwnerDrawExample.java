package de.vogella.jface.tableviewer;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import de.vogella.jface.tableviewer.model.ModelProvider;

public class ViewOwnerDrawExample extends ViewPart {

	public static final String ID = "de.vogella.jface.tableviewer.ownerdrawview";

	private TableViewer viewer;
	private static final Image ICON = getImage("preface.png");

	public void createPartControl(Composite parent) {
		new Label(parent, SWT.NONE).setText("Hello");
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		createViewer(parent);
	}

	private void createViewer(Composite parent) {

		// Define the TableViewer
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		// Create the columns
		createColumns(parent);

		// Make lines and make header visible
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// Set the ContentProvider
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		// Get the content for the Viewer,
		// setInput will call getElements in the ContentProvider
		viewer.setInput(ModelProvider.INSTANCE.getPersons());

		// Make the selection available to other Views
		getSite().setSelectionProvider(viewer);

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent) {

		String[] titles = { "Column 1"};
		int[] bounds = { 100, 100, 100, 100 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		final Display display = Display.getDefault();
		// ICON is an Image 
		col.setLabelProvider(new OwnerDrawLabelProvider() {

			@Override
			protected void measure(Event event, Object element) {
				Rectangle rectangle = ICON.getBounds();
				event.setBounds(new Rectangle(event.x, event.y, rectangle.width + 200 , rectangle.height));
			}

			@Override
			protected void paint(Event event, Object element) {
				Rectangle bounds = event.getBounds();
				event.gc.drawText("Hello", bounds.x, bounds.y);
				Point point = event.gc.stringExtent("Hello");
				event.gc.drawImage(ICON, bounds.x + 5 + point.x, bounds.y);
			}
			
		});

	}

	

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		// Create the menu item for this column
		return viewerColumn;
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private static Image getImage(String file) {
		Bundle bundle = FrameworkUtil.getBundle(ViewOwnerDrawExample.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}
