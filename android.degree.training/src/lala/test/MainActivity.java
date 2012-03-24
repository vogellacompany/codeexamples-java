package lala.test;

import android.app.Activity;
import android.degree.training.MyFingerPaintView;
import android.os.Bundle;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyFingerPaintView(this));
	}
}