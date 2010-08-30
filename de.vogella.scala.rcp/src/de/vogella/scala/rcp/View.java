package de.vogella.scala.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * JDT like to remove this import therefore I'm using the full qualified class
 * name below
 * 
 */
// import de.vogella.scala.plugin.ScalaView;
public class View extends ViewPart {
	public static final String ID = "de.vogella.scala.rcp.view";

	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		// This one creates and uses the Scala plug-in
		new de.vogella.scala.plugin.ScalaView(parent, SWT.NULL);
	}

	public void setFocus() {
	}
}