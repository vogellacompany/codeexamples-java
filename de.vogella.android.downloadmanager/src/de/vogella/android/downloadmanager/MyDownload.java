package de.vogella.android.downloadmanager;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class MyDownload extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		Request request = new Request(
				Uri.parse("http://www.vogella.de/img/lars/LarsVogelArticle7.png"));
		final long enqueue = dm.enqueue(request);
		BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
					intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
					Query query = new Query();
					query.setFilterById(enqueue);
					Cursor c = dm.query(query);
					if (c.moveToFirst()) {
						int columnIndex = c
								.getColumnIndex(DownloadManager.COLUMN_STATUS);
						if (DownloadManager.STATUS_SUCCESSFUL == c
								.getInt(columnIndex)) {
							ImageView view = (ImageView) findViewById(R.id.imageView1);
						}
					}
				}
			}
		};
	}
}