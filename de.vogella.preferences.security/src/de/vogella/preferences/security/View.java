package de.vogella.preferences.security;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public void createPartControl(Composite parent) {
		Button buttonPut = new Button(parent, SWT.PUSH);
		buttonPut.setText("Save values");
		buttonPut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISecurePreferences preferences = SecurePreferencesFactory
						.getDefault();
				ISecurePreferences node = preferences.node("info");
				try {
					node.put("user", "vogella", true);
					node.put("password", "123", true);
				} catch (StorageException e1) {
					e1.printStackTrace();
				}
			}
		});
		Button buttonGet = new Button(parent, SWT.PUSH);
		buttonGet.setText("Get values");
		buttonGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISecurePreferences preferences = SecurePreferencesFactory
						.getDefault();
				if (preferences.nodeExists("info")) {
					ISecurePreferences node = preferences.node("info");
					try {
						String user = node.get("user", "n/a");
						String password = node.get("password", "n/a");
						System.out.println(user);
						System.out.println(password);
					} catch (StorageException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}