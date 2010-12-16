package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CTabFolderExample {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		shell.setLayout(new GridLayout());
		CTabFolder folder = new CTabFolder(shell, SWT.NONE);
		GridData data = new GridData(GridData.FILL, GridData.BEGINNING, true,
				false, 2, 1);
		folder.setLayoutData(data);
		CTabItem cTabItem1 = new CTabItem(folder, SWT.NONE);
		cTabItem1.setText("Tab1");
		CTabItem cTabItem2 = new CTabItem(folder, SWT.NONE);
		cTabItem2.setText("Tab2");
		CTabItem cTabItem3 = new CTabItem(folder, SWT.NONE);
		cTabItem3.setText("Tab3");
		
		Text text = new Text(folder, SWT.BORDER);
		text.setText("Hello");
		cTabItem1.setControl(text);
		
		shell.pack ();
		shell.open ();

		shell.open();
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
