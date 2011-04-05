package net.androgames.level.config;

import net.androgames.level.R;
import net.androgames.level.orientation.OrientationProvider;
import net.androgames.level.orientation.provider.ProviderAccelerometer;
import net.androgames.level.orientation.provider.ProviderOrientation;

public enum Provider {

	ORIENTATION(R.string.orientation, R.string.orientation_summary, R.string.orientation_sensor),
	ACCELEROMETER(R.string.accelerometer, R.string.accelerometer_summary, R.string.accelerometer_sensor);

	private int label;
	private int summary;
	private int name;
	
	private Provider(int label, int summary, int name) {
		this.label = label;
		this.name = name;
		this.summary = summary;
	}
	
	public int getSummary() {
		return summary;
	}

	public int getName() {
		return name;
	}
	
	public int getLabel() {
		return label;
	}

	public OrientationProvider getProvider() {
		switch (this) {
			case ACCELEROMETER : return ProviderAccelerometer.getInstance();
			case ORIENTATION : return ProviderOrientation.getInstance();
		}
		return null;
	}
	
}
