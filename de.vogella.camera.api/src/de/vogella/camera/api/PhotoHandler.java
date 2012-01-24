package de.vogella.camera.api;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class PhotoHandler implements PictureCallback {

	private final Context context;

	public PhotoHandler(Context context) {
		this.context = context;
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File pictureFileDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"SimpleSelfCam");

		if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

			Log.d(Constants.DEBUG_TAG, "Can't create directory to save image");
			Toast.makeText(context, "Can't make path to save pic.",
					Toast.LENGTH_LONG).show();
			return;

		}

		String filename = pictureFileDir.getPath() + File.separator
				+ "latest_mug.jpg";
		File pictureFile = new File(filename);

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
			Toast.makeText(context, "Image saved as latest_mug.jpg",
					Toast.LENGTH_LONG).show();
		} catch (Exception error) {
			Log.d(Constants.DEBUG_TAG, "File not saved: " + error.getMessage());
			Toast.makeText(context, "Can't save image.", Toast.LENGTH_LONG)
					.show();
		}
	}
}
