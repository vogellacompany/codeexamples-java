package com.vogella.android.mockito.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView view = (TextView) findViewById(R.id.target);
		MyApp application = (MyApp) getApplication();
		view.setText(String.valueOf(application.getNumber()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static Intent createQuery(Context context, String query, String value) {
		Intent i = new Intent(context, MyListActivity.class);
		i.putExtra("QUERY", query);
		i.putExtra("VALUE", value);
		return i;
	}

}
