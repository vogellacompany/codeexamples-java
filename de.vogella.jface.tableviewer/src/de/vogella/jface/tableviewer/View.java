package de.vogella.jface.tableviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.vogella.jface.tableviewer.edit.GenderEditingSupport;
import de.vogella.jface.tableviewer.edit.LastNameEditingSupport;
import de.vogella.jface.tableviewer.edit.MarriedEditingSupport;
import de.vogella.jface.tableviewer.filter.PersonFilter;
import de.vogella.jface.tableviewer.model.ModelProvider;
import de.vogella.jface.tableviewer.model.Person;
import de.vogella.jface.tableviewer.sorter.MyViewerComparator;
import de.vogella.jface.tableviewer.util.SearchUtil;

public class View extends ViewPart {
	public static final String ID = "de.vogella.jface.tableviewer.view";

	private TableViewer viewer;
	// We use icons
	private static final Image CHECKED = Activator.getImageDescriptor(
			"icons/checked.gif").createImage();
	private static final Image UNCHECKED = Activator.getImageDescriptor(
			"icons/unchecked.gif").createImage();

	private static Color colorYellow = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);

	private Text searchText;
	private MyViewerComparator comparator;
	private PersonFilter filter;

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}

		});

		createViewer(parent);
		comparator = new MyViewerComparator();
		viewer.setComparator(comparator);
		filter = new PersonFilter();
		viewer.addFilter(filter);

	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		ColumnViewerEditorActivationStrategy actStrategy = new ColumnViewerEditorActivationStrategy(
				viewer) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
						// || event.eventType ==
						// ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED);
			}

		};

		final TableViewerFocusCellManager focusCellMgr = new TableViewerFocusCellManager(
				viewer, new FocusCellOwnerDrawHighlighter(viewer));

		TableViewerEditor.create(viewer, focusCellMgr, actStrategy,
				ColumnViewerEditor.TABBING_HORIZONTAL
						| ColumnViewerEditorActivationEvent.TRAVERSAL
						| ColumnViewerEditor.KEYBOARD_ACTIVATION);

		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(ModelProvider.getInstance().getPersons());
		// Make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "First name", "Last name", "Gender", "Married" };
		int[] bounds = { 100, 100, 100, 100 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				String search = searchText.getText();
				Person person = (Person) cell.getElement();
				String cellText = person.getFirstName();
				cell.setText(cellText);
				if (search != null && search.length() > 0) {
					int intRangesCorrectSize[] = SearchUtil
							.getSearchTermOccurrences(search, cellText);
					List<StyleRange> styleRange = new ArrayList<StyleRange>();
					for (int i = 0; i < intRangesCorrectSize.length / 2; i++) {
						int start = intRangesCorrectSize[i];
						int length = intRangesCorrectSize[++i];
						StyleRange myStyledRange = new StyleRange(start,
								length, null, colorYellow);

						styleRange.add(myStyledRange);
					}
					cell.setStyleRanges(styleRange
							.toArray(new StyleRange[styleRange.size()]));
				} else {
					cell.setStyleRanges(null);
				}

				super.update(cell);

			}
		});

		// Second column is for the last name
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getLastName();
			}
		});
		col.setEditingSupport(new LastNameEditingSupport(viewer));

		// Now the gender
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getGender();
			}
		});

		col.setEditingSupport(new GenderEditingSupport(viewer));

		// // Now the status married
		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if (((Person) element).isMarried()) {
					return CHECKED;
				} else {
					return UNCHECKED;
				}
			}
		});
		col.setEditingSupport(new MarriedEditingSupport(viewer));

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
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;

	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = viewer.getTable().getSortDirection();
				if (viewer.getTable().getSortColumn() == column) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {

					dir = SWT.DOWN;
				}
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}