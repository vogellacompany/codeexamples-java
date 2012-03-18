package android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SendReceiverActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		EditText view = (EditText) findViewById(R.id.editText1);
		Bundle extras = getIntent().getExtras();
		String string = extras.getString(Intent.EXTRA_TEXT);
		view.setText(string);
	}
}
