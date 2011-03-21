package de.vogella.android.sqlite.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDbAdapter {

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_COMMENT = "comment";
	private static final String DATABASE_TABLE = "comments";
	private Context context;
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	public CommentsDbAdapter(Context context) {
		this.context = context;
	}

	public CommentsDbAdapter open() throws SQLException {
		dbHelper = new MySQLiteHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long createComment(String comment) {
		ContentValues values = new ContentValues();
		values.put(KEY_COMMENT, comment);
		return database.insert(DATABASE_TABLE, null, values);
	}

	public Cursor fetchAllComments() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_COMMENT }, null, null, null,
				null, null);
	}
	
}
