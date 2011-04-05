package net.androgames.level.view;

import net.androgames.level.LevelPreferences;
import net.androgames.level.config.DisplayType;
import net.androgames.level.config.Provider;
import net.androgames.level.config.Viscosity;
import net.androgames.level.orientation.Orientation;
import net.androgames.level.thread.LevelThread;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 
 * A Bubble level for Android phones
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public class LevelView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener {

	private LevelThread drawer;

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setFocusable(true);
    	setOnTouchListener(this);
    }

    public LevelThread getThread() {
        return drawer;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (drawer != null) {
        	drawer.pause(!hasWindowFocus);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	if (drawer != null) {
    		drawer.setSurfaceSize(width, height);
    	}
    }

    public void surfaceCreated(SurfaceHolder holder) {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    	if (drawer == null) {
	    	drawer = new LevelThread(holder, getContext(), new Handler(), getWidth(), getHeight(), 
					prefs.getBoolean(LevelPreferences.KEY_SHOW_ANGLE, true), 
					DisplayType.valueOf(prefs.getString(LevelPreferences.KEY_DISPLAY_TYPE, "ANGLE")),
					Viscosity.valueOf(prefs.getString(LevelPreferences.KEY_VISCOSITY, "MEDIUM")).getValue(),
					prefs.getBoolean(LevelPreferences.KEY_LOCK, false),
					prefs.getBoolean(LevelPreferences.KEY_ECONOMY, false),
					Provider.valueOf(prefs.getString(LevelPreferences.KEY_SENSOR, "ORIENTATION")));
	    }
    }

    // TODO : voir si on ne met pas juste en pause et vidage dans le finalize...
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (drawer != null) {
        	drawer.pause(true);
        	drawer.clean();
	        drawer = null;
        }
        // free resources
        System.gc();
    }

    public void onOrientationChanged(Orientation orientation, float pitch, float roll) {
		if (drawer != null) {
			drawer.onOrientationChanged(orientation, pitch, roll);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN && drawer != null) {
			drawer.onTouch((int) event.getX(), (int) event.getY());
		}
		return true;
	}

}
