package android.jax.live;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private static final int PICK_PHOTO = 10;
	private Bitmap bitmap;
	private MyDrawingView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyDrawingView(this);
		setContentView(view);
		if (BuildConfig.DEBUG) {
			Log.wtf("Hello", "Testring");
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return true;
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_pickphoto:
			// Hole photo
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, PICK_PHOTO);

			break;
		case R.id.menuitem_red:
			// RED
			break;
		case R.id.menuitem_white:
			// WHITE
			break;
		case R.id.menuitem_save:
			view.buildDrawingCache();
			Bitmap cache = view.getDrawingCache();
			saveBitmap(cache);
			view.destroyDrawingCache();
			break;

		default:
			break;
		}
		return true;
	}

	private void saveBitmap(Bitmap cache) {
		File dir = Environment.getExternalStorageDirectory();
		File file = new File(dir, "hallojaxpicture.jpg");
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(file);
			cache.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
			// Add to Media ContentProvider
			fOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Check request and resultCode
		InputStream stream = null;
		try {
			stream = getContentResolver().openInputStream(data.getData());
			if (bitmap != null) {
				view.setBitmap(null);
				bitmap.recycle();
				bitmap = null;
			}
			bitmap = BitmapFactory.decodeStream(stream);
			view.setBitmap(bitmap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
}