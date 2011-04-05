package net.androgames.level.config;

import net.androgames.level.R;

public enum Viscosity {

	HIGH(R.string.viscosity_high_summary, R.integer.viscosity_high),
	MEDIUM(R.string.viscosity_medium_summary, R.integer.viscosity_medium),
	LOW(R.string.viscosity_low_summary, R.integer.viscosity_low);

	private int summary;
	private int value;
	
	private Viscosity(int summary, int value) {
		this.summary = summary;
		this.value = value;
	}
	
	public int getSummary() {
		return summary;
	}

	public int getValue() {
		return value;
	}
	
}
