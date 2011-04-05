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
// TODO : passer en abstract class avec une methode static qui gere l'orientation
public interface OrientationProvider {

	public boolean isListening();
	
	public boolean isSupported();
	
	public void resetCalibration();
	
	public void setCalibration(float...values);
	
	public void startListening(OrientationListener listener);
	
	public void stopListening();
	
}
