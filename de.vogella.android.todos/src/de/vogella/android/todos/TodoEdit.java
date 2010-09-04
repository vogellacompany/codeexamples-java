package de.vogella.android.todos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.vogella.android.todos.database.TodoDbAdapter;

public class TodoEdit extends Activity {
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;
	private TodoDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
	    mDbHelper = new TodoDbAdapter(this);
        mDbHelper.open();
		setContentView(R.layout.todo_edit);
		mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
		mBodyText = (EditText) findViewById(R.id.todo_edit_description);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
		mRowId = null;
		Bundle extras = getIntent().getExtras();
		mRowId = (bundle == null) ? null : (Long) bundle
				.getSerializable(TodoDbAdapter.KEY_ROWID);
		if (extras != null) {
			mRowId = extras.getLong(TodoDbAdapter.KEY_ROWID);
		}
		System.out.println("So far so good");
		populateFields();
		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Bundle bundle = new Bundle();
				bundle.putString(TodoDbAdapter.KEY_SUMMARY, mTitleText
						.getText().toString());
				bundle.putString(TodoDbAdapter.KEY_DESCRIPTION, mBodyText
						.getText().toString());
				if (mRowId != null) {
					bundle.putLong(TodoDbAdapter.KEY_ROWID, mRowId);
				}

				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();
			}

		});
	}

	private void populateFields() {
		if (mRowId != null) {
			Cursor todo = mDbHelper.fetchTodo(mRowId);
			startManagingCursor(todo);
			mTitleText.setText(todo.getString(todo
					.getColumnIndexOrThrow(TodoDbAdapter.KEY_SUMMARY)));
			mBodyText.setText(todo.getString(todo
					.getColumnIndexOrThrow(TodoDbAdapter.KEY_DESCRIPTION)));
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(TodoDbAdapter.KEY_ROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();

		if (mRowId == null) {
			long id = mDbHelper.createTodo(summary, description);
			if (id > 0) {
				mRowId = id;
			}
		} else {
			mDbHelper.updateTodo(mRowId, summary, description);
		}
	}
}
