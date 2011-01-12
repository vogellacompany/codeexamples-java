package de.vogella.android.wallpaper;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class WallpaperPreferencesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}
}
