package de.vogella.modelnotification.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public enum ContentProvider {
	INSTANCE;
	List<String> model = new ArrayList<String>();

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	// Used to update all views which are listening to the global model
	public void notifyListeners(Object source, String propertyName) {
		PropertyChangeEvent event = new PropertyChangeEvent(source,
				propertyName, null, null);
		for (PropertyChangeListener listener : propertyChangeSupport
				.getPropertyChangeListeners()) {
			listener.propertyChange(event);
		}
	}

}
