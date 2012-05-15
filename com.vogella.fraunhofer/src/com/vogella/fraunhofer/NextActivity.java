package com.vogella.fraunhofer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class NextActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypref);
	}
}
