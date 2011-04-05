package net.androgames.level.config;

import net.androgames.level.R;

public enum DisplayType {

	ANGLE(R.string.angle, R.string.angle_summary, "00.0", "88.8", 99.9f),
	INCLINATION(R.string.inclination, R.string.inclination_summary, "000.0", "888.8", 999.9f),
	ROOF_PITCH(R.string.roof_pitch, R.string.roof_pitch_summary, "00.000", "88.888", 99.999f);
	
	private int label;
	private int summary;
	private float max;
	private String displayFormat;
	private String displayBackgroundText;
	
	private DisplayType(int label, int summary, String displayFormat, String displayBackgroundText, float max) {
		this.label = label;
		this.max = max;
		this.summary = summary;
		this.displayFormat = displayFormat;
		this.displayBackgroundText = displayBackgroundText;
	}
	
	public float getMax() {
		return max;
	}
	
	public int getSummary() {
		return summary;
	}

	public int getLabel() {
		return label;
	}

	public String getDisplayFormat() {
		return displayFormat;
	}
	
	public String getDisplayBackgroundText() {
		return displayBackgroundText;
	}
	
}
