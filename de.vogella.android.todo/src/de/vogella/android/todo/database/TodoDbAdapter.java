package de.vogella.android.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TodoDbAdapter  {

    // Database fields
    public static final String KEY_ROWID = "_id";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_DESCRIPTION = "description";
    private static final String DATABASE_TABLE = "todo";
	private Context context;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;
    
    public TodoDbAdapter(Context context) {
        this.context = context;
    }
    
    public TodoDbAdapter open() throws SQLException {
        dbHelper= new DatabaseHelper(context);
        database =  dbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
    	 dbHelper.close();
    }


    /**
     * Create a new todo If the todo is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    public long createTodo(String title, String body) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SUMMARY, title);
        initialValues.put(KEY_DESCRIPTION, body);

        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     * 
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteTodo(long rowId) {

        return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all todo in the database
     * The cursor represents a light representation of the data 
     * memory efficient -> the cursor will retrieve and release data as it is needed
     * 
     * @return Cursor over all notes
     */
    public Cursor fetchAllTodos() {

        return database.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SUMMARY,
                KEY_DESCRIPTION}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchTodo(long rowId) throws SQLException {

        Cursor mCursor =

                database.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                        KEY_SUMMARY, KEY_DESCRIPTION}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     * 
     * @param rowId id of note to update
     * @param title value to set note title to
     * @param body value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateTodo(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_SUMMARY, title);
        args.put(KEY_DESCRIPTION, body);

        return database.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
