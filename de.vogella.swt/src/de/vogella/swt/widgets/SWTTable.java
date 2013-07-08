package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class SWTTable {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		shell.setLayout(new GridLayout());


		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		String[] titles = { "First Name", "Last Name", "Age" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			table.getColumn(i).pack();
		}
		
		for (int i = 0 ; i<= 50 ; i++){
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText (0, "Person " +i );
			item.setText (1, "LastName " +i );
			item.setText (2, String.valueOf(i));
		}
		
		shell.pack ();
		shell.open ();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
