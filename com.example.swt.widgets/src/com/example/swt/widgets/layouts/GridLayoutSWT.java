package com.example.swt.widgets.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class GridLayoutSWT {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		// create a new GridLayout with two columns
		// of different size
		GridLayout layout = new GridLayout(2, false);

		// set the layout to the shell
		shell.setLayout(layout);
		
		// Create a label and a button
		Label label = new Label(shell, SWT.NONE);
		label.setText("A label");
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Press Me");

		// Create a new label that will span two columns
		label = new Label(shell, SWT.BORDER);
		label.setText("This is a label");
		// Create new layout data
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
		label.setLayoutData(data);

		// Create a new label which is used as a separator
		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		// Create new layout data
		data = new GridData(SWT.FILL, SWT.TOP, true, false);
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		// Create a right aligned button
		Button b = new Button(shell, SWT.PUSH);
		b.setText("New Button");

		data = new GridData(SWT.BOTTOM, SWT.TOP, false, false, 2, 1);
		b.setLayoutData(data);
		
		 // create a spinner with min value 0 and max value 1000
		Spinner spinner = new Spinner(shell, SWT.READ_ONLY);
		spinner.setMinimum(0);
		spinner.setMaximum(1000);
		spinner.setSelection(500);
		spinner.setIncrement(1);
		spinner.setPageIncrement(100);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.widthHint = SWT.DEFAULT;
		gridData.heightHint = SWT.DEFAULT;
		gridData.horizontalSpan = 2;
		spinner.setLayoutData(gridData);

		Composite composite = new Composite(shell, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(1, false));

		Text txtTest = new Text(composite, SWT.NONE);
		txtTest.setText("Testing");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		txtTest.setLayoutData(gridData);

		Text txtMoreTests = new Text(composite, SWT.NONE);
		txtMoreTests.setText("Another test");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("This is my group");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		Text txtAnotherTest = new Text(group, SWT.NONE);
		txtAnotherTest.setText("Another test");

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
