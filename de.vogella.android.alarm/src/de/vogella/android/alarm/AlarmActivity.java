package de.vogella.android.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AlarmActivity extends Activity {
	private NumberPicker minutesPicker;
	private NumberPicker hoursPicker;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		minutesPicker = (NumberPicker) findViewById(R.id.minutes);
		minutesPicker.setRange(0, 60);
		hoursPicker = (NumberPicker) findViewById(R.id.hours);
		hoursPicker.setRange(0, 60);
	}

	public void startAlert(View view) {
		int minutes = minutesPicker.getCurrent()*60;
		Log.e("Hello", String.valueOf(minutes));
		int hours = hoursPicker.getCurrent()*60*60;
		Log.e("Hello", String.valueOf(hours));
		Intent intent = new Intent(this, MyBroadcastReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				this.getApplicationContext(), 234324243, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ ((minutes+hours) * 1000), pendingIntent);
		int sum = minutes +hours; 
		Toast.makeText(this, "Alarm set in "+ sum  + " seconds",
				Toast.LENGTH_LONG).show();
	}

}