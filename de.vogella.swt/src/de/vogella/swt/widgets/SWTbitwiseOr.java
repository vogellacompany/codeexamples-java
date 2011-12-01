package de.vogella.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SWTbitwiseOr {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		new Text(shell, SWT.NONE);
		System.out.println(SWT.NONE);
		System.out.println(SWT.MULTI);
		System.out.println(SWT.SINGLE);
		System.out.println(SWT.READ_ONLY);
		System.out.println(SWT.WRAP);
		System.out.println(SWT.SEARCH);
		System.out.println(SWT.ICON_CANCEL);
		System.out.println(SWT.ICON_SEARCH);
		System.out.println(SWT.LEFT);
		System.out.println(SWT.RIGHT);
		System.out.println(SWT.PASSWORD);
		System.out.println(SWT.CENTER);

		// Lets see what we have

		int result = SWT.MULTI | SWT.WRAP;
		System.out.println(result);
		if (SWT.MULTI == (result & SWT.MULTI)) {
			System.out.println("SWT.MULI available");
		}
		if (SWT.WRAP == (result & SWT.WRAP)) {
			System.out.println("SWT.WRAP available");
		}
		if (SWT.SINGLE == (result & SWT.SINGLE)) {
			System.out.println("SWT.SINGLE available");
		} else {
			System.out.println("SWT.SINGLE available");
		}
	}
}
