package net.androgames.level;

import net.androgames.level.config.DisplayType;
import net.androgames.level.config.Provider;
import net.androgames.level.config.Viscosity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;

/**
 * 
 * A Bubble level for Android phones
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public class LevelPreferences extends PreferenceActivity implements OnPreferenceChangeListener {

	public static final String KEY_SHOW_ANGLE 			= "preference_show_angle";
	public static final String KEY_DISPLAY_TYPE 		= "preference_display_type";
	public static final String KEY_SOUND 				= "preference_sound";
	public static final String KEY_LOCK				 	= "preference_lock";
	public static final String KEY_LOCK_LOCKED		 	= "preference_lock_locked";
	public static final String KEY_LOCK_ORIENTATION 	= "preference_lock_orientation";
	public static final String KEY_APPS					= "preference_apps";
	public static final String KEY_DONATE				= "preference_donate";
	public static final String KEY_SENSOR				= "preference_sensor";
	public static final String KEY_VISCOSITY			= "preference_viscosity";
	public static final String KEY_ECONOMY				= "preference_economy";

	private static final String PUB_APPS 	= "market://search?q=pub:\"Antoine Vianey\"";
	private static final String PUB_DONATE 	= "market://details?id=net.androgames.level.donate";
	
	private static final int DIALOG_CALIBRATE_AGAIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        if (true) {
	    	PreferenceCategory appsCategory = new PreferenceCategory(this);
	    	appsCategory.setTitle(R.string.preference_apps_category);
	    	Preference appsPreference = new Preference(this);
	    	appsPreference.setTitle(R.string.preference_apps);
	    	appsPreference.setSummary(R.string.preference_apps_summary);
	    	appsPreference.setKey(KEY_APPS);
	    	Preference donatePreference = new Preference(this);
	    	donatePreference.setTitle(R.string.preference_donate);
	    	donatePreference.setSummary(R.string.preference_donate_summary);
	    	donatePreference.setKey(KEY_DONATE);
	    	getPreferenceScreen().addPreference(appsCategory);
	    	appsCategory.addPreference(donatePreference);
	    	appsCategory.addPreference(appsPreference);
        }
    }

    public void onResume() {
    	super.onResume();
    	// enregistrement des listerners
    	findPreference(KEY_DISPLAY_TYPE).setOnPreferenceChangeListener(this);
    	findPreference(KEY_SENSOR).setOnPreferenceChangeListener(this);
    	findPreference(KEY_VISCOSITY).setOnPreferenceChangeListener(this);
    	findPreference(KEY_ECONOMY).setOnPreferenceChangeListener(this);
    	// mise a jour de l'affichage
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	findPreference(KEY_DISPLAY_TYPE).setSummary(DisplayType.valueOf(
    			prefs.getString(LevelPreferences.KEY_DISPLAY_TYPE, "ANGLE")).getSummary()); 
    	findPreference(KEY_SENSOR).setSummary(Provider.valueOf(
    			prefs.getString(LevelPreferences.KEY_SENSOR, "ORIENTATION")).getSummary());
    	findPreference(KEY_VISCOSITY).setSummary(Viscosity.valueOf(
    			prefs.getString(LevelPreferences.KEY_VISCOSITY, "MEDIUM")).getSummary());
    	findPreference(KEY_VISCOSITY).setEnabled(
				!((CheckBoxPreference) findPreference(KEY_ECONOMY)).isChecked());
        if (true) {
	    	// lancement du market
	    	findPreference(KEY_APPS).setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(PUB_APPS));
					LevelPreferences.this.startActivity(intent);
					return true;
				}
			});
	    	findPreference(KEY_DONATE).setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(PUB_DONATE));
					try {
						LevelPreferences.this.startActivity(intent);
					} catch (ActivityNotFoundException anfe) {}
					return true;
				}
			});
        }
    }

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		String key = preference.getKey();
		if (KEY_DISPLAY_TYPE.equals(key)) {
			preference.setSummary(DisplayType.valueOf((String) newValue).getSummary());
		} else if (KEY_SENSOR.equals(key)) {
			preference.setSummary(Provider.valueOf((String) newValue).getSummary());
			showDialog(DIALOG_CALIBRATE_AGAIN);
		} else if (KEY_VISCOSITY.equals(key)) {
			preference.setSummary(Viscosity.valueOf((String) newValue).getSummary());
		} else if (KEY_ECONOMY.equals(key)) {
			findPreference(KEY_VISCOSITY).setEnabled(!((Boolean) newValue));
		}
		return true;
	}
	
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch(id) {
	        case DIALOG_CALIBRATE_AGAIN:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setTitle(R.string.calibrate_again_title)
	        			.setIcon(android.R.drawable.ic_dialog_alert)
	        			.setCancelable(true)
	        	       	.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
	        	           	public void onClick(DialogInterface dialog, int id) {
	        	        	   	dialog.dismiss();
	        	           	}
	        	       	})
	        	       	.setMessage(R.string.calibrate_again_message);
	        	dialog = builder.create();
	            break;
	        default:
	            dialog = null;
        }
        return dialog;
    }
    
}
