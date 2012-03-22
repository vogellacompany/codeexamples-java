package de.vogella.android.userinterface;

import java.io.IOException;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageButton refreshButton = (ImageButton) findViewById(R.id.refresh);
		View parent = findViewById(R.id.layout);
		parent.post(new Runnable() {
			public void run() {
				// Post in the parent's message queue to make sure the parent
				// lays out its children before we call getHitRect()
				Rect delegateArea = new Rect();
				ImageButton delegate = refreshButton;
				delegate.getHitRect(delegateArea);
				delegateArea.top -= 600;
				delegateArea.bottom += 600;
				delegateArea.left -= 600;
				delegateArea.right += 600;
				TouchDelegate expandedArea = new TouchDelegate(delegateArea,
						delegate);
				// give the delegate to an ancestor of the view we're
				// delegating the
				// area to
				if (View.class.isInstance(delegate.getParent())) {
					((View) delegate.getParent())
							.setTouchDelegate(expandedArea);
				}
			};
		});
		RoundImageView photo = (RoundImageView) findViewById(R.id.editablePhoto1);
		try {
			photo.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(
					"lars.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClick(View view) {
		Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
	}
}