package de.vogella.android.ownservice;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ServiceConsumerActivity extends ListActivity {
	private WordService s;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		wordList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				wordList);
		setListAdapter(adapter);
		doBindService();
		Window window = getWindow();

		// List<String> wordList = s.getWordList();
		// Toast.makeText(this, wordList.get(0), Toast.LENGTH_LONG).show();
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder binder) {
			s = ((WordService.MyBinder) binder).getService();
			Toast.makeText(ServiceConsumerActivity.this, "Connected",
					Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className) {
			s = null;
		}
	};
	private ArrayAdapter<String> adapter;
	private List<String> wordList;

	void doBindService() {
		bindService(new Intent(this, WordService.class), mConnection,
				Context.BIND_AUTO_CREATE);
	}

	public void showServiceData(View view) {
		if (s != null) {
			Toast.makeText(this, s.getWordList().get(0), Toast.LENGTH_SHORT)
					.show();
			wordList.clear();
			wordList.addAll(s.getWordList());
			adapter.notifyDataSetChanged();
		}
	}

}