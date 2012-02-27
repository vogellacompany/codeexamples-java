package com.example.rcp.demo.views;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

public class Snippet010OwnerDraw {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display, SWT.CLOSE);
		shell.setSize(400, 400);
		shell.setLayout(new GridLayout());

		Snippet010OwnerDraw example = new Snippet010OwnerDraw();
		example.createPartControl(shell);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static int COLUMN_COUNT = 3;

	class CountryEntry {

		String name;

		String cupYear;

		private String baseName;

		/**
		 * Create a new instance of the receiver.
		 * 
		 * @param countryName
		 * @param worldCupYear
		 */
		CountryEntry(String countryName, String englishName, String worldCupYear) {
			name = countryName;
			cupYear = worldCupYear;
			baseName = englishName;
		}

		/**
		 * @param index
		 * @return
		 */
		public int getHeight(Event event) {
			switch (event.index) {
			case 0:
				return event.gc.textExtent(name).y;
			case 1:
				return 50;
			case 2:
				return event.gc.textExtent(cupYear).y;
			default:
				return 10;
			}
		}

		/**
		 * @param index
		 * @return
		 */
		public int getWidth(Event event) {

			switch (event.index) {
			case 0:
				return event.gc.textExtent(getDisplayString().toString()).x + 4;

			case 1:
				return 200;

			case 2:
				return event.gc.textExtent(cupYear).x + 5;

			default:
				return 10;
			}
		}

		/**
		 * Draw the flag in bounds.
		 * 
		 * @param event
		 */
		protected void drawFlag(Event event) {
			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_BLUE));

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			event.gc.fillRectangle(bounds);
		}

		/**
		 * Draw the cup year
		 * 
		 * @param event
		 */
		private void drawCupYear(Event event) {
			event.gc.drawText(cupYear, event.x, event.y);

		}

		/**
		 * Draw the name of the receiver.
		 * 
		 * @param event
		 */
		protected void drawName(Event event) {

			StringBuffer buffer = getDisplayString();

			Display display = viewer.getControl().getDisplay();
			TextLayout layout = new TextLayout(display);
			layout.setText(buffer.toString());

			TextStyle plain = new TextStyle(
					JFaceResources.getFont(JFaceResources.DEFAULT_FONT),
					display.getSystemColor(SWT.COLOR_LIST_FOREGROUND),
					display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

			TextStyle italic = new TextStyle(JFaceResources.getFontRegistry()
					.getItalic(JFaceResources.DEFAULT_FONT),
					display.getSystemColor(SWT.COLOR_BLUE),
					display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

			layout.setStyle(plain, 0, name.length() - 1);
			layout.setStyle(italic, name.length(), buffer.length() - 1);

			layout.draw(event.gc, event.x, event.y);

		}

		/**
		 * @return
		 */
		private StringBuffer getDisplayString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(name);
			buffer.append(" (");
			buffer.append(baseName);
			buffer.append(")");
			return buffer;
		}

		/**
		 * @param event
		 */
		public void draw(Event event) {

			switch (event.index) {
			case 0:
				drawName(event);
				break;
			case 1:
				drawFlag(event);
				break;
			case 2:
				drawCupYear(event);
				break;

			default:
				break;
			}

		}
	}

	private class GermanyEntry extends CountryEntry {

		GermanyEntry() {
			super("Deutschland", "Germany", "1954 1974 1990");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.tests.viewers.OwnerDrawExample.CountryEntry#drawFlag
		 * (org.eclipse.swt.widgets.Event)
		 */
		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			int stripeHeight = bounds.height / 3;
			Rectangle stripe = new Rectangle(bounds.x, bounds.y, bounds.width,
					stripeHeight);

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_YELLOW));
			event.gc.fillRectangle(stripe);

		}

	}

	private class AustriaEntry extends CountryEntry {

		AustriaEntry() {
			super("\u00D6sterreich", "Austria", "TBD");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.tests.viewers.OwnerDrawExample.CountryEntry#drawFlag
		 * (org.eclipse.swt.widgets.Event)
		 */
		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			int stripeHeight = bounds.height / 3;
			Rectangle stripe = new Rectangle(bounds.x, bounds.y, bounds.width,
					stripeHeight);

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_WHITE));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

		}
	}

	private class EnglandEntry extends CountryEntry {
		EnglandEntry() {
			super("Blighty", "England", "1966");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.tests.viewers.OwnerDrawExample.CountryEntry#drawFlag
		 * (org.eclipse.swt.widgets.Event)
		 */
		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(new Rectangle(bounds.width / 2 + bounds.x
					- 5, bounds.y, 10, bounds.height));
			event.gc.fillRectangle(new Rectangle(bounds.x, bounds.height / 2
					+ bounds.y - 5, bounds.width, 10));

		}
	}

	private TableViewer viewer;

	private CountryEntry[] entries;

	public Snippet010OwnerDraw() {
		entries = new CountryEntry[3];
		entries[0] = new AustriaEntry();
		entries[1] = new GermanyEntry();
		entries[2] = new EnglandEntry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.FULL_SELECTION);

		viewer.setContentProvider(new IStructuredContentProvider() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
			 */
			public void dispose() {
			};

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.IStructuredContentProvider#getElements
			 * (java.lang.Object)
			 */
			public Object[] getElements(Object inputElement) {
				return entries;
			};

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
			 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			public void inputChanged(org.eclipse.jface.viewers.Viewer viewer,
					Object oldInput, Object newInput) {
			}

		});
		createColumns();

		viewer.setLabelProvider(new OwnerDrawLabelProvider() {

			protected void measure(Event event, Object element) {
				CountryEntry country = (CountryEntry) element;

				event.setBounds(new Rectangle(event.x, event.y, country
						.getWidth(event), country.getHeight(event)));

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.OwnerDrawLabelProvider#paint(org.eclipse
			 * .swt.widgets.Event, java.lang.Object)
			 */
			protected void paint(Event event, Object element) {
				CountryEntry entry = (CountryEntry) element;
				entry.draw(event);

			}
		});

		OwnerDrawLabelProvider.setUpOwnerDraw(viewer);
		viewer.setInput(this);
		GridData data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH);

		viewer.getControl().setLayoutData(data);

		viewer.setSelection(new StructuredSelection(entries[1]));
	}

	/**
	 * Create the columns to be used in the tree.
	 */
	private void createColumns() {
		TableLayout layout = new TableLayout();
		viewer.getTable().setLayout(layout);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		for (int i = 0; i < COLUMN_COUNT; i++) {
			TableColumn tc = new TableColumn(viewer.getTable(), SWT.NONE, i);
			layout.addColumnData(new ColumnPixelData(100));
			tc.setText(getTitleFor(i));
		}
		;
	}

	/**
	 * @param i
	 * @return
	 */
	private String getTitleFor(int i) {
		switch (i) {
		case 0:
			return "Name";
		case 1:
			return "Flag";
		case 2:
			return "World Cup Year";
		}
		return "Unknown";
	}

	public void setFocus() {

	}

}