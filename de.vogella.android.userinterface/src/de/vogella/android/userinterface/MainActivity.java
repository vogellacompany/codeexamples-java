package de.vogella.android.userinterface;

import java.io.IOException;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageButton refreshButton;
	private Handler handler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		refreshButton = (ImageButton) findViewById(R.id.refresh);
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

	}

	public void onClick(View view) {
		final RoundImageView photo = (RoundImageView) findViewById(R.id.editablePhoto1);
		final View progress = findViewById(R.id.progressBar1);
		progress.setVisibility(View.VISIBLE);
		refreshButton.setVisibility(View.GONE);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				handler.post(new Runnable() {
					@Override
					public void run() {
						try {
							photo.setImageBitmap(BitmapFactory
									.decodeStream(getAssets().open("lars.png")));
							progress.setVisibility(View.GONE);
							refreshButton.setVisibility(View.VISIBLE);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				});

			}
		};

		new Thread(runnable).start();

		Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();

	}
}