package de.vogella.task.model;

import java.beans.PropertyChangeListener;
import java.util.Calendar;

/**
 * Represents a tasks which needs to be done
 * 
 * @author Lars Vogel
 * 
 */
public interface ITask {

	/**
	 * register property change listeners
	 */
	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Removes property change listener
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @return the id
	 */
	long getId();

	/**
	 * @return the summary
	 */
	String getSummary();

	/**
	 * @param summary
	 *            the summary to set
	 */
	void setSummary(String summary);

	/**
	 * @return the description
	 */
	String getDescription();

	/**
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * @return the startDate
	 */
	Calendar getStartDate();

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	void setStartDate(Calendar startDate);

	/**
	 * @return the dueDate
	 */
	Calendar getDueDate();

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	void setDueDate(Calendar dueDate);

	/**
	 * @return the priority
	 */
	Priority getPriority();

	/**
	 * @param priority
	 *            the priority to set
	 */
	void setPriority(Priority priority);

	/**
	 * @return the status
	 */
	Status getStatus();

	/**
	 * @param priority
	 *            the priority to set
	 */
	void setStatus(Status status);

}