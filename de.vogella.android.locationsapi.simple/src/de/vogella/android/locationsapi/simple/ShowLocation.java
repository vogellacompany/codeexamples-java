package de.vogella.android.locationsapi.simple;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowLocation extends Activity {
	private TextView latituteField;
	private TextView longitudeField;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		latituteField = (TextView) findViewById(R.id.TextView02);
		longitudeField = (TextView) findViewById(R.id.TextView04);
	}

	public void showLocation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				System.out.println("GPS is active...");
				int lat = (int) (location.getLatitude());
				int lng = (int) (location.getLongitude());
				latituteField.setText(String.valueOf(lat));
				longitudeField.setText(String.valueOf(lng));
			} else {
				latituteField.setText("GPS not available");
				longitudeField.setText("GPS not available");
			}
			break;
		}

	}

}