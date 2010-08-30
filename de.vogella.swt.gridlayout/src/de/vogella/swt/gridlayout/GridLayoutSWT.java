package de.vogella.swt.gridlayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GridLayoutSWT {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		// Create a new Gridlayout with 2 columns where the 2 column do no need
		// to be same size
		GridLayout layout = new GridLayout(2, false);
		// set the layout of the shell
		shell.setLayout(layout);

		// Create a label and a button
		Label label = new Label(shell, SWT.NONE);
		label.setText("A lable");
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Press Me");
		
		// Create a new label that will spam two columns
		label = new Label(shell, SWT.BORDER);
		label.setText("This is a label");
		// Create new layout data
		GridData data = new GridData(GridData.FILL, GridData.BEGINNING, true,
				false, 2, 1);
		label.setLayoutData(data);
		
		// Create a new label which is used as a separator
		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		// Create new layout data
		data = new GridData(GridData.FILL, GridData.BEGINNING, true,
				false, 2, 1);
		data.horizontalSpan=2;
		label.setLayoutData(data);
		
		// Create a right alligned button
		Button b = new Button(shell, SWT.PUSH);
		b.setText("New Button");
		
		data = new GridData(GridData.END, GridData.BEGINNING, false,
				false, 2, 1);
		b.setLayoutData(data);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
