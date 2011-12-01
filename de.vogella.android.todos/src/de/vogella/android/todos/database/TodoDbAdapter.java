package de.vogella.android.todos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TodoDbAdapter {

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_SUMMARY = "summary";
	public static final String KEY_DESCRIPTION = "description";
	private static final String DB_TABLE = "todo";
	private Context context;
	private SQLiteDatabase db;
	private TodoDatabaseHelper dbHelper;

	public TodoDbAdapter(Context context) {
		this.context = context;
	}

	public TodoDbAdapter open() throws SQLException {
		dbHelper = new TodoDatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Create a new todo If the todo is successfully created return the new
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 */
	public long createTodo(String category, String summary, String description) {
		ContentValues values = createContentValues(category, summary,
				description);

		return db.insert(DB_TABLE, null, values);
	}

	/**
	 * Update the todo
	 */
	public boolean updateTodo(long rowId, String category, String summary,
			String description) {
		ContentValues values = createContentValues(category, summary,
				description);

		return db.update(DB_TABLE, values, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Deletes todo
	 */
	public boolean deleteTodo(long rowId) {
		return db.delete(DB_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all todo in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllTodos() {
		return db.query(DB_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY,
				KEY_SUMMARY, KEY_DESCRIPTION }, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the defined todo
	 */
	public Cursor fetchTodo(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DB_TABLE, new String[] { KEY_ROWID,
				KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION }, KEY_ROWID + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(String category, String summary,
			String description) {
		ContentValues values = new ContentValues();
		values.put(KEY_CATEGORY, category);
		values.put(KEY_SUMMARY, summary);
		values.put(KEY_DESCRIPTION, description);
		return values;
	}

}
