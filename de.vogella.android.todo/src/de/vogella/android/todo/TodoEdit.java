package de.vogella.android.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.vogella.android.todo.database.TodoDbAdapter;

public class TodoEdit extends Activity {
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.todo_edit);
		mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
		mBodyText = (EditText) findViewById(R.id.todo_edit_description);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
		mRowId = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String title = extras.getString(TodoDbAdapter.KEY_SUMMARY);
		    String body = extras.getString(TodoDbAdapter.KEY_DESCRIPTION);
		    mRowId = extras.getLong(TodoDbAdapter.KEY_ROWID);
		           
		    if (title != null) {
		        mTitleText.setText(title);
		    }
		    if (body != null) {
		        mBodyText.setText(body);
		    }
		}
		confirmButton.setOnClickListener(new View.OnClickListener() {
		
		    public void onClick(View view) {
		    	Bundle bundle = new Bundle();
	            bundle.putString(TodoDbAdapter.KEY_SUMMARY, mTitleText.getText().toString());
	            bundle.putString(TodoDbAdapter.KEY_DESCRIPTION, mBodyText.getText().toString());
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
}
