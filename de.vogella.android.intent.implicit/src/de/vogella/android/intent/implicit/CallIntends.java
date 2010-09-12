package de.vogella.android.intent.implicit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
					Uri.parse("http://www.vogella.de"));
			startActivity(intent);
			break;
		case R.id.Button02:
			intent = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:(+49)12345789"));
			startActivity(intent);
			break;
		case R.id.Button03:
			intent = new Intent(Intent.ACTION_DIAL,
					Uri.parse("tel:(+49)12345789"));
			startActivity(intent);
			break;
		case R.id.Button04:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:50.123,7.1434?z=19"));
			startActivity(intent);
			break;
		case R.id.Button05:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=query"));
			startActivity(intent);
			break;
		case R.id.Button06:
			 intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, 0);
			break;
		case R.id.Button07:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
			startActivity(intent);
			break;
		case R.id.Button08:
			intent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == Activity.RESULT_OK && requestCode == 0) {
	    String result = data.toURI();
	    Toast.makeText(this, result, Toast.LENGTH_LONG);
	  }
	}
}