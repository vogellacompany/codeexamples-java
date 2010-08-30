package de.vogella.swt.dnd;

import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MyDropTargetListener implements DropTargetListener {

	private Composite parentComposite;
	private DropTarget target;

	/**
	 * @param parentComposite
	 *            - the composite that holds all pictures
	 * @param target
	 *            - the drop target
	 */
	public MyDropTargetListener(Composite parentComposite, DropTarget target) {
		this.parentComposite = parentComposite;
		this.target = target;
	}

	public void dragEnter(DropTargetEvent event) {
	}

	public void dragOver(DropTargetEvent event) {
	}

	public void dragLeave(DropTargetEvent event) {
	}

	public void dropAccept(DropTargetEvent event) {
	}

	public void dragOperationChanged(DropTargetEvent event) {
	}

	/**
	 * This method moves the dragged picture to the new position and shifts the
	 * old picture to the right or left.
	 */
	public void drop(DropTargetEvent event) {

		// retrieve the stored index
		int sourceIndex = Integer.valueOf(event.data.toString());

		// compute the index of target control
		Control targetControl = target.getControl();
		int targetIndex = -1;
		for (int i = 0; i < parentComposite.getChildren().length; i++) {
			if (parentComposite.getChildren()[i].equals(targetControl)) {
				targetIndex = i;
				break;
			}
		}

		Control sourceControl = parentComposite.getChildren()[sourceIndex];
		// do not do anything if the dragged photo is dropped at the same
		// position
		if (targetIndex == sourceIndex)
			return;

		// if dragged from left to right
		// shift the old picture to the left
		if (targetIndex > sourceIndex)
			sourceControl.moveBelow(targetControl);
		// if dragged from right to left
		// shift the old picture to the right
		else
			sourceControl.moveAbove(targetControl);

		// repaint the parent composite
		parentComposite.layout();
	}

}
