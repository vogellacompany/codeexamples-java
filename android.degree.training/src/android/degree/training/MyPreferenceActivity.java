package android.degree.training;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import test.test.test1.R;

public class MyPreferenceActivity extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.moreprefs);
	}
}
