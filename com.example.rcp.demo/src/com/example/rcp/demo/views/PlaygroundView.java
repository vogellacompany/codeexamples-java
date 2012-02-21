package com.example.rcp.demo.views;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import com.example.rcp.demo.model.ModelService;
import com.example.rcp.demo.model.Person;

public class PlaygroundView extends ViewPart {

	public static String ID = "com.example.rcp.demo.views.playground";
	private List<Person> list;

	@Override
	public void createPartControl(final Composite parent) {

		parent.setLayout(new GridLayout(4, false));
		list = ModelService.getInstance();
		final TableViewer viewer = new TableViewer(parent, SWT.HORIZONTAL
				| SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		Menu headerMenu = new Menu(parent.getShell(), SWT.POP_UP);
		viewer.getTable().setMenu(headerMenu);
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText("Firstname");
		column.getColumn().setWidth(200);
		createMenuItem(headerMenu, column.getColumn());
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getFirstName();
			}

		});

		column.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				Person p = (Person) element;
				p.setFirstName(value.toString());
				getViewer().refresh();
			}

			@Override
			protected Object getValue(Object element) {
				Person p = (Person) element;
				return p.getFirstName();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				Person p = (Person) element;
				TableViewer viewer = (TableViewer) getViewer();
				TextCellEditor editor = new TextCellEditor(viewer.getTable());
				return editor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
		column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText("Nachname");
		column.getColumn().setWidth(200);
		createMenuItem(headerMenu, column.getColumn());

		column.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				StyledString text = new StyledString();
				StyleRange myStyledRange = new StyleRange(17, 2, null, Display
						.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
				text.append("This is a test", StyledString.DECORATIONS_STYLER);
				text.append(" ( " + 15 + " ) ", StyledString.DECORATIONS_STYLER);
				cell.setText(text.toString());

				StyleRange[] range = { myStyledRange };
				cell.setStyleRanges(range);
				super.update(cell);
			}
		});
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Button btnAdd = new Button(parent, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.add(new Person("Lars", "Vogel"));
				viewer.refresh();
			}
		});
		btnAdd.setText("Add");

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(list);

	}

	@Override
	public void setFocus() {

	}

	// Add a new Menu Entry to the menu for a column
	private void createMenuItem(Menu parent, final TableColumn column) {
		final MenuItem itemName = new MenuItem(parent, SWT.CHECK);
		itemName.setText(column.getText());
		itemName.setSelection(column.getResizable());
		itemName.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (itemName.getSelection()) {
					column.setWidth(150);
					column.setResizable(true);
				} else {
					column.setWidth(0);
					column.setResizable(false);
				}
			}
		});

	}

}
