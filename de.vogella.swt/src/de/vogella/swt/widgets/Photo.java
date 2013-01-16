package de.vogella.swt.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Photo {

	public static void main(String[] args) {

		// setup the SWT window
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		shell.setText("Photo Application");

		// initialize a parent composite with a grid layout manager
		// with 5x columns
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		parent.setLayout(gridLayout);

		// Get the Display default icons
		List<Image> imageList = new ArrayList<Image>();

		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WARNING));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WORKING));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_QUESTION));
		imageList
				.add(Display.getDefault().getSystemImage(SWT.ICON_INFORMATION));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_ERROR));

		// Alternative load images via
		// Image img = new Image(display, path + imgNames[i]);
		// to get a path
		// String path = System.getProperty("user.dir") + "/images/";
		for (Image image : imageList) {
			Label label = new Label(parent, SWT.NONE);
			label.setImage(image);
		}
		// show the SWT window

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		// tear down the SWT window
		display.dispose();
		// if you don't use system images you would have to release them
		// not necessary in this example
		// for (Image image : imageList) {
		// if (image != null) {
		// image.dispose();
		// }
		// }
	}
}
