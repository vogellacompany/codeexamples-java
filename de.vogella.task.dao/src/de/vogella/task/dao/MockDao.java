package de.vogella.task.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.vogella.task.model.Factory;
import de.vogella.task.model.ITask;
import de.vogella.task.model.Priority;
import de.vogella.task.model.Status;

public enum MockDao {
	INSTANCE;

	private List<ITask> tasks;

	private MockDao() {
		tasks = new ArrayList<ITask>();
		ITask task;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2009, 03, 22);
		createTask("Prepare Jug Presentation", "Prepare Jug Presentation",
				calendar, Priority.HIGH, Status.NEW);
		calendar = GregorianCalendar.getInstance();
		calendar.set(2009, 03, 17);
		createTask("Fix NPE in Example", "Find this nasty bug", calendar,
				Priority.MEDIUM, Status.DONE);
		calendar = GregorianCalendar.getInstance();
		calendar.set(2009, 04, 01);
		createTask("Another Task", "Very important", calendar, Priority.LOW,
				Status.NEW);
		calendar = GregorianCalendar.getInstance();
		calendar.set(2009, 04, 01);
		createTask("Another Task II", "Very important", calendar,
				Priority.MEDIUM, Status.NEW);
		calendar = GregorianCalendar.getInstance();
		calendar.set(2009, 04, 01);
		createTask("Another Task III", "Very important", calendar,
				Priority.HIGH, Status.STARTED);

	}

	private void createTask(String summary, String description,
			Calendar calendar, Priority prio, Status status) {
		ITask task = Factory.createRequirementInstance();
		task.setSummary(summary);
		task.setDescription(description);
		task.setDueDate(calendar);
		task.setPriority(prio);
		task.setStatus(status);
		tasks.add(task);
	}

	public List<ITask> getTasks() {
		return tasks;
	}

	public void persists() {
		// Doing nothing....
	}
}
