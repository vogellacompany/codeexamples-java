package de.vogella.android.sqlite.first;

import java.util.Random;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class TestDatabase extends ListActivity {
    private CommentsDbAdapter db;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // From this column of the table
        String[] from = new String[] { CommentsDbAdapter.KEY_COMMENT};
		// To which view element will this be mapped
        int[] to = new int[] { android.R.id.text1 };
        db = new CommentsDbAdapter(this);
        db.open();
        
        // Create a new comment every time the activity is started
        
        String[] comments = new String[] {"Cool", "Very nice", "Hate it"};
    	int nextInt = new Random().nextInt(3);
    	db.createComment(comments[nextInt]);
        
        Cursor c = db.fetchAllComments();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, from, to);
        setListAdapter(adapter);
    }
    @Override
    protected void onPause() {
    	db.close();
    	super.onPause();
    }
    
}