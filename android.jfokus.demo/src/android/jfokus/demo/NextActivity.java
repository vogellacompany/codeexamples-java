package android.jfokus.demo;

import android.app.Activity;
import android.os.Bundle;

public class NextActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyTouchView(this));
	}

}
