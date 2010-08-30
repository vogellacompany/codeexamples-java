package de.vogella.dnd.jface.view;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.vogella.dnd.jface.dnd.MyDragListener;
import de.vogella.dnd.jface.model.ContentProvider;
import de.vogella.dnd.jface.viewers.TableContentProvider;
import de.vogella.dnd.jface.viewers.TableLabelProvider;

public class TableView extends ViewPart {


	@Override
	public void createPartControl(Composite parent) {
		TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		int operations = DND.DROP_COPY| DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
		viewer.addDragSupport(operations, transferTypes , new MyDragListener(viewer));
		viewer.setContentProvider(new TableContentProvider());
		viewer.setLabelProvider(new TableLabelProvider());
		viewer.setInput(ContentProvider.INSTANCE.getModel());
	}

	@Override
	public void setFocus() {

	}

}
