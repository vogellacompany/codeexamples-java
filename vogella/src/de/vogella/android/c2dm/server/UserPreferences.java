package de.vogella.android.c2dm.server;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypreferences);
	}
}
