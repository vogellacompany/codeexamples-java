package de.vogella.android.todos;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import de.vogella.android.todos.database.TodoDbAdapter;

public class Todos extends ListActivity {
	private TodoDbAdapter mDbHelper;
	private int mTodoNumber = 1;
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	public static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private Cursor cursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
		mDbHelper = new TodoDbAdapter(this);
		mDbHelper.open();
		fillData();
		registerForContextMenu(getListView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		return result;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createTodo();
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createTodo();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			mDbHelper.deleteTodo(info.id);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	private void createTodo() {
		Intent i = new Intent(this, TodoEdit.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor c = cursor;
		c.moveToPosition(position);
		Intent i = new Intent(this, TodoEdit.class);
		i.putExtra(TodoDbAdapter.KEY_ROWID, id);
		i.putExtra(TodoDbAdapter.KEY_SUMMARY, c.getString(c
				.getColumnIndexOrThrow(TodoDbAdapter.KEY_SUMMARY)));
		i.putExtra(TodoDbAdapter.KEY_DESCRIPTION, c.getString(c
				.getColumnIndexOrThrow(TodoDbAdapter.KEY_DESCRIPTION)));
		startActivityForResult(i, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_CREATE:
			String title = extras.getString(TodoDbAdapter.KEY_SUMMARY);
			String body = extras.getString(TodoDbAdapter.KEY_DESCRIPTION);
			mDbHelper.createTodo(title, body);
			fillData();
			break;
		case ACTIVITY_EDIT:
			Long mRowId = extras.getLong(TodoDbAdapter.KEY_ROWID);
			if (mRowId != null) {
				String editTitle = extras.getString(TodoDbAdapter.KEY_SUMMARY);
				String editBody = extras
						.getString(TodoDbAdapter.KEY_DESCRIPTION);
				mDbHelper.updateTodo(mRowId, editTitle, editBody);
			}
			fillData();
			break;
		}

	}

	private void fillData() {
		cursor = mDbHelper.fetchAllTodos();
		startManagingCursor(cursor);

		String[] from = new String[] { TodoDbAdapter.KEY_SUMMARY };
		int[] to = new int[] { R.id.text1 };

		// Now create an array adapter and set it to display using our row
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.todo_row, cursor, from, to);
		setListAdapter(notes);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}
}