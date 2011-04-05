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
public interface OrientationListener {

	public void onOrientationChanged(Orientation orientation, float pitch, float roll);
	
}
