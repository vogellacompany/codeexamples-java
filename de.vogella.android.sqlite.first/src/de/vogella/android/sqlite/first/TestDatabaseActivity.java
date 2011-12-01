package de.vogella.android.sqlite.first;

import java.util.Random;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class TestDatabaseActivity extends ListActivity {
	private CommentsDbAdapter db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Define the columns of the table
		// which should be used in the ListView
		String[] from = new String[] { CommentsDbAdapter.KEY_COMMENT };
		// Define the view elements to which the
		// columns will be mapped
		int[] to = new int[] { android.R.id.text1 };
		db = new CommentsDbAdapter(this);
		db.open();

		// Create a new comment every time the activity is started
		String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
		int nextInt = new Random().nextInt(3);
		// Save the new comment to the database
		db.createComment(comments[nextInt]);

		// Read all comments
		Cursor c = db.fetchAllComments();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c, from, to);
		setListAdapter(adapter);
	}

	@Override
	protected void onPause() {
		db.close();
		super.onPause();
	}

}