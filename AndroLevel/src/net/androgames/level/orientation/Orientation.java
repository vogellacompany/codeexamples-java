package net.androgames.level.orientation;

/**
 * 
 * A Bubble level for Android phones
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public enum Orientation {

	LANDING(1, 0),
	TOP(1, 0),
	RIGHT(1, 90),
	BOTTOM(-1, 180),
	LEFT(-1, 270);
	
	private int reverse;
	private int rotation;
	
	public int getReverse() {
		return reverse;
	}

	public int getRotation() {
		return rotation;
	}

	private Orientation(int reverse, int rotation) {
		this.reverse = reverse;
		this.rotation = rotation;
	}
	
	public boolean isLevel(float pitch, float roll) {
		switch (this) {
			case BOTTOM :
			case TOP :
				return roll < 0.1 && roll > - 0.1;
			case LANDING :
				return roll < 0.1 && roll > - 0.1 && (Math.abs(pitch) < 0.1 || Math.abs(pitch) > 179.9);
			case LEFT :
			case RIGHT :
				return Math.abs(pitch) < 0.1 || Math.abs(pitch) > 179.9;
		}
		return false;
	}
	
}
