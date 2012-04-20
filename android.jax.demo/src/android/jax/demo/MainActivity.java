package android.jax.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private static final int PICK_IMAGE = 10;
	private MyDrawingView myDrawingView;
	private Bitmap bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myDrawingView = new MyDrawingView(this);
		setContentView(myDrawingView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem1_pickpicture:
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, PICK_IMAGE);
			break;
		case R.id.menuitem_redcolor:
			myDrawingView.setDrawingColor(Color.RED);
			break;
		case R.id.menuitem_savescreenshot:
			myDrawingView.buildDrawingCache();
			Bitmap drawingCache = myDrawingView.getDrawingCache();
			saveToSdCard(drawingCache);
			myDrawingView.destroyDrawingCache();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveToSdCard(Bitmap drawingCache) {
		OutputStream fOut = null;
		File file = new File(Environment.getExternalStorageDirectory(),
				"screenshot.jpg");
		if (file.exists()) {
			file.delete();
		}
		try {
			fOut = new FileOutputStream(file);
			drawingCache.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
			fOut.flush();
			fOut.close();
			MediaStore.Images.Media.insertImage(getContentResolver(),
					file.getAbsolutePath(), file.getName(), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		InputStream stream = null;

		if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
			try {
				Log.w("TEST", String.valueOf(requestCode));
				Log.w("TEST", String.valueOf(resultCode));
				stream = getContentResolver().openInputStream(data.getData());
				if (bitmap != null) {
					myDrawingView.setBitmap(null);
					bitmap.recycle();
				}

				bitmap = BitmapFactory.decodeStream(stream);
				Log.w("TEST", "Setting the Bitmap");
				myDrawingView.setBitmap(bitmap);

			} catch (FileNotFoundException e) {
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
		}
	};

}