package com.linkesoft.bbingo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Verwaltung von Benutzereinstellungen
 *
 */
public class Prefs {
	private static final String ID="id";
	public static void setID(Context ctx,long id)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(ID, id);
		editor.commit();
	}
	public static long getID(Context ctx) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		return prefs.getLong(ID,0);	// default ist 0, d.h. keine ID	
	}
}
