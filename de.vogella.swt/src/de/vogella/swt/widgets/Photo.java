package de.vogella.swt.widgets;

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
		shell.setSize(520, 200);
		shell.setLayout(new RowLayout());
		shell.setText("Photo Application");

		// initialize a parent composite with a grid layout manager
		// since the demo application uses 4x pictures the grid has exactly
		// 4x columns
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		parent.setLayout(gridLayout);

		// determine the path where the pictures are stored
		String path = System.getProperty("user.dir") + "/images/";
		// initialize an array with the photograph names
		String[] imgNames = new String[] { "lars.png", "andre.png",
				"matthias.png", "arne.png" };

		// loop over the photo array and establish all listeners
		for (int i = 0; i < imgNames.length; i++) {
			// labels serve as containers for the images
			Label label = new Label(parent, SWT.NONE);
			Image img = new Image(display, path + imgNames[i]);
			label.setImage(img);
		}

		// show the SWT window
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		// tear down the SWT window
		display.dispose();
	}
}
