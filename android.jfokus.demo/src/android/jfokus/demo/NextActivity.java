package android.jfokus.demo;

import android.app.ListActivity;
import android.os.Bundle;

public class NextActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyTouchView(this));
	}

}
