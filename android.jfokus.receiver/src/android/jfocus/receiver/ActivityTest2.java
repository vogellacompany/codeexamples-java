package android.jfocus.receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityTest2 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		String string = bundle.getString(Intent.EXTRA_TEXT);
	}
}