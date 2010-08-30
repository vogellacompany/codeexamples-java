package de.vogella.android.intent.implicit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class CallIntends extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void callIntent(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.Button01:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.sap.de"));
			startActivity(intent);
			break;
		case R.id.Button02:
			intent = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:(+49)12345789"));
			startActivity(intent);
			break;
		case R.id.Button03:
			intent = new Intent(Intent.ACTION_SEND,
					Uri.parse("geo:50.123,7.1434?z=19"));
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}