package de.vogella.task.model.intern;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;

import de.vogella.task.model.ITask;
import de.vogella.task.model.Priority;
import de.vogella.task.model.Status;

/**
 * Domain model, represents a tasks which needs to be done
 * 
 * @author Lars Vogel
 * 
 */
public class Task implements ITask {
	private final long id;
	private String summary;
	private String description;
	private Calendar startDate;
	private Calendar dueDate;
	private Priority priority;
	private Status status;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Task(long id) {
		this.id = id;
		// Setting default values
		priority = Priority.MEDIUM;
		status = Status.NEW;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getId()
	 */
	public long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getSummary()
	 */
	public String getSummary() {
		return summary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#setSummary(java.lang.String)
	 */
	public void setSummary(String summary) {
		propertyChangeSupport.firePropertyChange("summary", this.summary,
				this.summary = summary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		propertyChangeSupport.firePropertyChange("description",
				this.description, this.description = description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getStartDate()
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#setStartDate(java.util.Calendar)
	 */
	public void setStartDate(Calendar startDate) {
		propertyChangeSupport.firePropertyChange("startDate", this.startDate,
				this.startDate = startDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getDueDate()
	 */
	public Calendar getDueDate() {
		return dueDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#setDueDate(java.util.Calendar)
	 */
	public void setDueDate(Calendar dueDate) {
		propertyChangeSupport.firePropertyChange("dueDate", this.dueDate,
				this.dueDate = dueDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.vogella.task.model.ITask#getPriority()
	 */
	public Priority getPriority() {
		return priority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.vogella.task.model.ITask#setPriority(de.vogella.task.model.Priority)
	 */
	public void setPriority(Priority priority) {
		propertyChangeSupport.firePropertyChange("priority", this.priority,
				this.priority = priority);
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Status status) {
		propertyChangeSupport.firePropertyChange("status", this.status,
				this.status = status);
	}

}
