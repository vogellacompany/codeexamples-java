package net.androgames.level.orientation.provider;

import java.util.List;

import net.androgames.level.Level;
import net.androgames.level.orientation.Orientation;
import net.androgames.level.orientation.OrientationListener;
import net.androgames.level.orientation.OrientationProvider;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 
 * A Bubble level for Android phones
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public class ProviderOrientation implements OrientationProvider {
	
	private static OrientationProvider provider;

	private static Sensor sensor;
	private static SensorManager sensorManager;
	private static OrientationListener listener;

	/** indicates whether or not Orientation Sensor is supported */
	private static Boolean supported;
	/** indicates whether or not Orientation Sensor is running */
	private static boolean running = false;
	
	/** Calibration */
	private static float calibratedPitch;
	private static float calibratedRoll;
	
	private ProviderOrientation() {}
	
	public static OrientationProvider getInstance() {
		if (provider == null) {
			provider = new ProviderOrientation();
		}
		return provider;
	}

	public void setCalibration(float...values) {
		calibratedPitch += values[0];
		calibratedRoll += values[1];
	}

	/**
	 * Returns true if the manager is listening to orientation changes
	 */
	public boolean isListening() {
		return running;
	}

	/**
	 * Unregisters listeners
	 */
	public void stopListening() {
		running = false;
		try {
			if (sensorManager != null && sensorEventListener != null) {
				sensorManager.unregisterListener(sensorEventListener);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Returns true if at least one Orientation sensor is available
	 */
	public boolean isSupported() {
		if (supported == null) {
			if (Level.getContext() != null) {
				sensorManager = (SensorManager) Level.getContext().getSystemService(Context.SENSOR_SERVICE);
				List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
				supported = new Boolean(sensors.size() > 0);
			} else {
				supported = Boolean.FALSE;
			}
		}
		return supported;
	}

	/**
	 * Registers a listener and start listening
	 */
	public void startListening(OrientationListener orientationListener) {
		sensorManager = (SensorManager) Level.getContext().getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		if (sensors.size() > 0) {
			sensor = sensors.get(0);
			running = sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
			listener = orientationListener;
		}
	}

	/**
	 * The listener that listen to events from the orientation listener
	 */
	private static SensorEventListener sensorEventListener = new SensorEventListener() {
        
		private Orientation orientation;
        private float pitch;
        private float roll;

		public void onAccuracyChanged(Sensor sensor, int accuracy) {}

		public void onSensorChanged(SensorEvent event) {
			
            pitch = event.values[1] - calibratedPitch;     	// pitch
            roll = event.values[2] - calibratedRoll;      	// roll
 
            if (pitch < -45 && pitch > -135) {
                // top side up
                orientation = Orientation.TOP;
            } else if (pitch > 45 && pitch < 135) {
                // bottom side up
                orientation = Orientation.BOTTOM;
            } else if (roll > 45) {
                // right side up
                orientation = Orientation.RIGHT;
            } else if (roll < -45) {
                // left side up
                orientation = Orientation.LEFT;
            } else {
            	// landing
            	orientation = Orientation.LANDING;
            }
            
            listener.onOrientationChanged(orientation, pitch, roll);
		}

	};

	/**
	 * Reset the calibration
	 */
	public void resetCalibration() {
		calibratedPitch = 0;
		calibratedRoll = 0;
	}

}