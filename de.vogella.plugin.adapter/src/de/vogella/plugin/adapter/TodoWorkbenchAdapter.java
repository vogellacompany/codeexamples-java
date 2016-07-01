package de.vogella.plugin.adapter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchAdapter;

import de.vogella.plugin.adapter.model.Todo;

public class TodoWorkbenchAdapter extends WorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public StyledString getStyledText(Object object) {
		if (object instanceof Todo) {
			Todo todo = (Todo) object;
			StyledString styledString = new StyledString(todo.getSummary());
			if (todo.isDone()) {
				Styler styler = new Styler() {

					@Override
					public void applyStyles(TextStyle textStyle) {
						// Todos, which are done should have a green background
						textStyle.background = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
					}
				};
				styledString.setStyle(0, todo.getSummary().length(), styler);

			}
			return styledString;
		}
		return super.getStyledText(object);
	}

}
