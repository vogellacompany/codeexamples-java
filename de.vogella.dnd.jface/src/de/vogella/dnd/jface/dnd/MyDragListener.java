package de.vogella.dnd.jface.dnd;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;

import de.vogella.dnd.jface.model.Todo;
public class MyDragListener implements DragSourceListener {

	private final TableViewer viewer;

	public MyDragListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("Finshed Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// Here you do the convertion to the type which is expected.
		IStructuredSelection selection = (IStructuredSelection) viewer
		.getSelection();
		Todo firstElement = (Todo) selection.getFirstElement();
		
		if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = firstElement.getShortDescription() + " " + firstElement.getLongDescription(); 
		}

	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("Start Drag");
	}

}
