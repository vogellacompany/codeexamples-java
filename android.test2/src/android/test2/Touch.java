package android.test2;

import android.app.Activity;
import android.os.Bundle;

public class Touch extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawingView(this));
	}
}