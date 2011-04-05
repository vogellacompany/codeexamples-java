package net.androgames.level;

import net.androgames.level.config.Provider;
import net.androgames.level.orientation.Orientation;
import net.androgames.level.orientation.OrientationListener;
import net.androgames.level.orientation.OrientationProvider;
import net.androgames.level.view.LevelView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

/**
 * 
 * A Bubble level for Android phones
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public class Level extends Activity implements OrientationListener {
	
	private static Context CONTEXT;
	
	private static final int DIALOG_CALIBRATE_ID = 1;
	private static final int TOAST_DURATION = 10000;

	/** Calibration */
	// TODO : utiliser les valeurs 1, 2, 3 variables pour les prefs
	private static final String SAVED_PITCH = "net.androgames.level.pitch";
	private static final String SAVED_ROLL = "net.androgames.level.roll";
	
	private OrientationProvider provider;
	
    private LevelView view;
    private boolean calibrating;
    private WakeLock wakeLock;
    
	/** Gestion du son */
	private SoundPool soundPool;
	private boolean soundEnabled;
	private int bipSoundID;
	private int bipRate;
	private long lastBip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        CONTEXT = this;
        view = (LevelView) findViewById(R.id.level);
        calibrating = false;
        // sound
    	soundPool = new SoundPool(1, AudioManager.STREAM_RING, 0);
    	bipSoundID = soundPool.load(this, R.raw.bip, 1);
    	bipRate = getResources().getInteger(R.integer.bip_rate);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
    
    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        case R.id.calibrate:
	            showDialog(DIALOG_CALIBRATE_ID);
	            return true;
	        case R.id.preferences:
	            startActivity(new Intent(this, LevelPreferences.class));
	            return true;
        }
        return false;
    }
    
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch(id) {
	        case DIALOG_CALIBRATE_ID:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setTitle(R.string.calibrate_title)
	        			.setIcon(null)
	        			.setCancelable(true)
	        			.setPositiveButton(R.string.calibrate, new DialogInterface.OnClickListener() {
	        	           	public void onClick(DialogInterface dialog, int id) {
	        	        	   	Level.this.dismissDialog(DIALOG_CALIBRATE_ID);
	        	        	   	calibrating = true;
	        	           	}
	        			})
	        	       	.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	        	           	public void onClick(DialogInterface dialog, int id) {
	        	        	   	Level.this.dismissDialog(DIALOG_CALIBRATE_ID);
	        	           	}
	        	       	})
	        	       	.setNeutralButton(R.string.reset, new DialogInterface.OnClickListener() {
	        	           	public void onClick(DialogInterface dialog, int id) {
	        	           		provider.resetCalibration();
        	           			Level.this.saveCalibration(0, 0, true);
	        	           	}
	        	       	})
	        	       	.setMessage(R.string.calibrate_message);
	        	dialog = builder.create();
	            break;
	        default:
	            dialog = null;
        }
        return dialog;
    }
    
    protected void onResume() {
    	super.onResume();
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	provider = Provider.valueOf(
    			prefs.getString(LevelPreferences.KEY_SENSOR, "ORIENTATION")).getProvider();
    	// chargement des effets sonores
        soundEnabled = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
        		LevelPreferences.KEY_SOUND, false);
        // orientation manager
    	if (provider.isSupported()) {
    		provider.startListening(this);
    		prefs = getPreferences(Context.MODE_PRIVATE);
    		provider.resetCalibration();
    		provider.setCalibration(
				prefs.getFloat(SAVED_PITCH, 0), 
				prefs.getFloat(SAVED_ROLL, 0));
    	} else {
    		Toast.makeText(this, getText(R.string.not_supported), TOAST_DURATION).show();
    	}
        // wake lock
        wakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE)).newWakeLock(
        		PowerManager.SCREEN_BRIGHT_WAKE_LOCK, this.getClass().getName());
        wakeLock.acquire();
    }

    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (provider.isListening()) {
        	provider.stopListening();
    	}
		wakeLock.release();
    }
    
    @Override
    public void onDestroy() {
		if (soundPool != null) {
			soundPool.release();
		}
		super.onDestroy();
    }

	@Override
	public void onOrientationChanged(Orientation orientation, float pitch, float roll) {
		if (calibrating) {
			calibrating = false;
			saveCalibration(pitch, roll, false);
		}
		if (soundEnabled && orientation.isLevel(pitch, roll)
				&& System.currentTimeMillis() - lastBip > bipRate) {
			AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_RING);
			float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_RING);
			float volume = streamVolumeCurrent / streamVolumeMax;
			lastBip = System.currentTimeMillis();
			soundPool.play(bipSoundID, volume, volume, 1, 0, 1);
		}
		view.onOrientationChanged(orientation, pitch, roll);
	}
	
	private void saveCalibration(float pitch, float roll, boolean reset) {
		Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
		editor.putFloat(SAVED_PITCH, pitch);
		editor.putFloat(SAVED_ROLL, roll);
		int id = R.string.calibrate_failed;
		if (editor.commit()) {
			provider.setCalibration(pitch, roll);
			if (reset) {
				id = R.string.calibrate_restored;
			} else {
				id = R.string.calibrate_saved;
			}
		}
		Toast.makeText(this, id, TOAST_DURATION).show();
	}

    public static Context getContext() {
		return CONTEXT;
	}
    
}
