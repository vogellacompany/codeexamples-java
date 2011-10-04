package de.vogella.android.test.target;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencePage extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypreference);
	}
}
