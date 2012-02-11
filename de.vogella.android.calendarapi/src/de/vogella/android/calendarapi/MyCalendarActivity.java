package de.vogella.android.calendarapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

public class MyCalendarActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		Intent calIntent = new Intent(Intent.ACTION_INSERT);
		calIntent.setData(CalendarContract.Events.CONTENT_URI);
		startActivity(calIntent);
	}
}