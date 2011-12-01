package de.vogella.android.todos.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoTable {
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table todo "
			+ "(_id integer primary key autoincrement, "
			+ "category text not null, " + "summary text not null,"
			+ " description text not null);";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(TodoTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS todo");
		onCreate(database);
	}
}
