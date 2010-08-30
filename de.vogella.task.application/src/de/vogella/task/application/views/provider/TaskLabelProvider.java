package de.vogella.task.application.views.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.vogella.task.application.Application;
import de.vogella.task.model.ITask;
import de.vogella.task.model.Status;

public class TaskLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	// We use icons
	private static final Image ERROR = AbstractUIPlugin
			.imageDescriptorFromPlugin(Application.PLUGIN_ID, "icons/error.gif")
			.createImage();
	private static final Image WARNING = AbstractUIPlugin
			.imageDescriptorFromPlugin(Application.PLUGIN_ID,
					"icons/errorwarning.gif").createImage();
	private static final Image PROCESS = AbstractUIPlugin
			.imageDescriptorFromPlugin(Application.PLUGIN_ID,
					"icons/progress_spinner.gif").createImage();

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		ITask task = (ITask) element;

		if (columnIndex == 0) {
			return getIcons(task);
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ITask task = (ITask) element;
		switch (columnIndex) {
		case 0:
			return "";// String.valueOf(task.getId());
		case 1:
			SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
			return date_format.format(task.getDueDate().getTime());
		case 2:
			return task.getSummary();
		case 3:
			return task.getPriority().toString();
		case 4:
			return String.valueOf(task.getStatus());
		}
		throw new RuntimeException("LabelProvider: Should not happen");
	}

	private Image getIcons(ITask task) {
		if (!task.getStatus().equals(Status.DONE)) {
			Calendar today = GregorianCalendar.getInstance();
			if (task.getDueDate().after(today)) {
				return ERROR;
			}
			if (task.getStatus().equals(Status.STARTED)) {
				return PROCESS;
			}
			today.add(Calendar.DATE, 10);
			if (task.getDueDate().before(today)) {
				return WARNING;
			}
		}
		return null;
	}
}
