package de.vogella.camera.api;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import de.vogella.cameara.api.R;

public class MakePhotoActivityAdvanced extends Activity {
	private final static String DEBUG_TAG = "MakePhotoActivity";
	private Camera camera;
	private CameraView cameraView;
	private int cameraId = 0;
	private FrameLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// do we have a camera?
		if (!getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Toast.makeText(this, "No camera feature on this device",
					Toast.LENGTH_LONG).show();
		} else {

			cameraId = findFirstFrontFacingCamera();

			if (cameraId >= 0) {
				layout = (FrameLayout) findViewById(R.id.cameraPreview);
				layout.removeAllViews();
				startCameraInLayout(layout, cameraId);

			} else {
				Toast.makeText(this, "No front facing camera found.",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void onClick(View view) {
		camera.takePicture(null, null, new PhotoHandler(this));
	}

	public void faceDetection(View view) {
		Toast.makeText(this, "Started Face detection", Toast.LENGTH_SHORT);
		camera.startFaceDetection();

	}

	private int findFirstFrontFacingCamera() {
		int cameraId = -1;
		// search for the first front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				Log.d(DEBUG_TAG, "Found front facing camera");
				cameraId = i;
				break;
			}
		}
		return cameraId;
	}

	private void startCameraInLayout(FrameLayout layout, int cameraId) {
		camera = Camera.open(cameraId);
		if (camera != null) {
			cameraView = new CameraView(this, camera);
			layout.addView(cameraView);
		}
		camera.setFaceDetectionListener(new FaceDetectionListener() {

			@Override
			public void onFaceDetection(Face[] faces, Camera camera) {
				// Do something useful with the info
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (camera == null && layout != null) {
			layout.removeAllViews();
			startCameraInLayout(layout, cameraId);
		}
	}

	@Override
	protected void onPause() {
		if (camera != null) {
			camera.release();
			camera = null;
		}
		super.onPause();
	}

}
