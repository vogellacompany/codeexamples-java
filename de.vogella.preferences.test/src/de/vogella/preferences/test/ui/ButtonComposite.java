package de.vogella.preferences.test.ui;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ButtonComposite extends Composite {

	public ButtonComposite(Composite parent, int style) {
		super(parent, style);
		Button write = new Button(parent, SWT.PUSH);
		write.setText("Write");
		write.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Preferences preferences = ConfigurationScope.INSTANCE
						.getNode("de.vogella.preferences.test");
				Preferences sub1 = preferences.node("note1");
				Preferences sub2 = preferences.node("node2");
				sub1.put("h1", "Hello");
				sub1.put("h2", "Hello again");
				sub2.put("h1", "Moin");

				try {
					// Forces the application to save the preferences
					preferences.flush();
				} catch (BackingStoreException e2) {
					e2.printStackTrace();
				}
			}
		});
		Button read = new Button(parent, SWT.PUSH);
		read.setText("Read");
		read.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Preferences preferences = ConfigurationScope.INSTANCE
						.getNode("de.vogella.preferences.test");
				Preferences sub1 = preferences.node("note1");
				Preferences sub2 = preferences.node("node2");
				System.out.println(sub1.get("h1", "default"));
				System.out.println(sub1.get("h2", "default"));
				System.out.println(sub2.get("h1", "default"));

			}
		});

		Button clear = new Button(parent, SWT.PUSH);
		clear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Preferences preferences = ConfigurationScope.INSTANCE
						.getNode("de.vogella.preferences.test");
				Preferences sub1 = preferences.node("note1");
				Preferences sub2 = preferences.node("node2");
				// Delete the existing settings
				try {
					sub1.clear();
					sub2.clear();
					preferences.flush();
				} catch (BackingStoreException e1) {
					e1.printStackTrace();
				}
			}
		});
		clear.setText("clear");
	}
}
