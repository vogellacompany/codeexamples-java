package de.vogella.tutorial.maps2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class TutorialMapActivity extends MapActivity implements
		LocationListener {

	private MapView mapView;
	private LocationManager locationManager;
	private String provider;
	private MyLocationOverlay myLocationOverlay;
	private MarienplatzOverlay marienplatzOverlay;

	private class GeocoderTask extends AsyncTask<Void, Void, Void> {

		private GeoPoint geoPointMarienplatz;

		@Override
		protected Void doInBackground(Void... arg0) {
			Geocoder geocoder = new Geocoder(TutorialMapActivity.this,
					Locale.GERMAN);
			try {
				// String
				// locationName="1600 Amphitheatre Parkway, Mountain View, CA";
				String locationName = "Marienplatz, M�nchen";
				List<Address> addresses = geocoder.getFromLocationName(
						locationName, 1);
				if (addresses != null && !addresses.isEmpty()) {
					Address address = addresses.get(0);
					Log.d("MyMaps", "Found " + address);
					geoPointMarienplatz = convertToGeoPoint(
							address.getLatitude(), address.getLongitude());
				} else {
					Log.w("MyMaps", "No address found");
				}

			} catch (IOException e) {
				Log.e("MyMaps", "Geocoder failed", e);
			}
			if (geoPointMarienplatz == null) {
				// Auf mindestens einem Emulator geht das nicht, daher
				// hart-kodiert als Plan B
				geoPointMarienplatz = new GeoPoint(48137794, 11575941);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (geoPointMarienplatz != null) {
				marienplatzOverlay = new MarienplatzOverlay(
						TutorialMapActivity.this, geoPointMarienplatz);
				mapView.getOverlays().add(marienplatzOverlay);
				mapView.postInvalidate();
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initSpinnerPlace();
		initMapViewAndOverlay();

		new GeocoderTask().execute((Void) null);

		initLocationProvider();
	}

	private void initSpinnerPlace() {
		Spinner spinner = (Spinner) findViewById(R.id.spinnerPlace);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String[] latitudes = getResources().getStringArray(
						R.array.placesLatitude);
				if (latitudes[position].length() > 0) {
					String[] places = getResources().getStringArray(
							R.array.placesNames);
					String[] longitudes = getResources().getStringArray(
							R.array.placesLongitude);
					String place = places[position];
					double latitude = Double.parseDouble(latitudes[position]);
					double longitude = Double.parseDouble(longitudes[position]);
					Toast.makeText(
							TutorialMapActivity.this,
							place + " @ lat=" + latitude + ", lon=" + longitude,
							1000).show();
					jumpTo(latitude, longitude);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	protected void jumpTo(double latitude, double longitude) {
		GeoPoint geoPoint = convertToGeoPoint(latitude, longitude);
		// TODO Die MapView beim geoPoint zentrieren: ueber den Controller
		// animateTo aufrufen
	}

	private void initMapViewAndOverlay() {
		mapView = (MapView) findViewById(R.id.mapview);
		// TODO Den (built-in) Zoom aktivieren
		MapController mapController = mapView.getController();
		mapController.setZoom(10);

		myLocationOverlay = new MyLocationOverlay(this, mapView);
		// TODO Das Overlay myLocationOverlay der MapView hinzufuegen

		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapView.getController().animateTo(
						myLocationOverlay.getMyLocation());
			}
		});
	}

	private void initLocationProvider() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteriaCoarse = new Criteria();
		criteriaCoarse.setAccuracy(Criteria.ACCURACY_COARSE);
		Criteria criteriaFine = new Criteria();
		criteriaFine.setAccuracy(Criteria.ACCURACY_FINE);

		List<String> providers = locationManager.getProviders(true);
		Log.d("MyMaps", "Enabled providers: " + providers);

		String providerCoarse = locationManager.getBestProvider(criteriaCoarse,
				true);
		String providerFine = locationManager.getBestProvider(criteriaFine,
				true);

		Log.d("MyMaps", "Coarse provider: " + providerCoarse
				+ ", fine provider: " + providerFine);
		provider = providerCoarse != null ? providerCoarse : providerFine;
		if (provider == null) {
			Log.e("MyMaps", "No location provider");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (provider != null) {
			locationManager.requestLocationUpdates(provider, 500, 5, this);
		}
		// TODO myLocationOverlay: Compass und "MyLocation" "enablen"
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
		// TODO myLocationOverlay: Compass und "MyLocation" "disablen"
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d("MyMaps", "Got new location: " + location);
		GeoPoint point = convertToGeoPoint(location.getLatitude(),
				location.getLongitude());
		if (marienplatzOverlay != null) {
			marienplatzOverlay.setGeoPointCurrent(point);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mapView.postInvalidate();
				}
			});
		}
	}

	private GeoPoint convertToGeoPoint(double latitude, double longitude) {
		int latE6 = (int) (latitude * 1e6 + 0.5);
		int lonE6 = (int) (longitude * 1e6 + 0.5);
		return new GeoPoint(latE6, lonE6);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}