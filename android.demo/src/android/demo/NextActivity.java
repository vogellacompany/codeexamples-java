package android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NextActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String string = extras.getString("key1", "missing");
			Toast.makeText(this, string, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void finish() {
		Intent intent = new Intent();
		intent.putExtra("help", "lars");
		setResult(RESULT_OK, intent);
		super.finish();
	}
}
