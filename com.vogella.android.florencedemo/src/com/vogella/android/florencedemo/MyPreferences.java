package com.vogella.android.florencedemo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		addPreferencesFromResource(R.xml.myprefs);
		super.onCreate(savedInstanceState);
	}
}
