package de.vogella.android.loader.preferences;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesLoader extends AsyncTaskLoader<SharedPreferences>
		implements SharedPreferences.OnSharedPreferenceChangeListener {
	private SharedPreferences prefs = null;

	public static void persist(final SharedPreferences.Editor editor) {
		editor.apply();
	}

	public SharedPreferencesLoader(Context context) {
		super(context);
	}

	/**
	 * Runs on a worker thread, loading in our data.
	 */
	@Override
	public SharedPreferences loadInBackground() {
		prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		prefs.registerOnSharedPreferenceChangeListener(this);

		return (prefs);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		onContentChanged();
	}

	/**
	 * Starts an asynchronous load of the list data. When the result is ready
	 * the callbacks will be called on the UI thread. If a previous load has
	 * been completed and is still valid the result may be passed to the
	 * callbacks immediately.
	 * 
	 * Must be called from the UI thread.
	 */
	@Override
	protected void onStartLoading() {
		if (prefs != null) {
			deliverResult(prefs);
		}

		if (takeContentChanged() || prefs == null) {
			forceLoad();
		}
	}
}
