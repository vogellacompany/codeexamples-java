package de.vogella.camera.api;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
	private final static String DEBUG_TAG = "MirroView";
	private int cameraId = 0;
	private SurfaceHolder holder;
	private Camera camera;

	public CameraView(Context context, Camera camera) {
		super(context);
		this.camera = camera;
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception error) {
			Log.d(DEBUG_TAG,
					"Error starting mPreviewLayout: " + error.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		if (holder.getSurface() == null) {
			return;
		}

		// can't make changes while mPreviewLayout is active
		try {
			camera.stopPreview();
		} catch (Exception e) {

		}

		try {
			// set rotation to match device orientation
			setCameraDisplayOrientationAndSize();

			// start up the mPreviewLayout
			camera.setPreviewDisplay(holder);
			camera.startPreview();

		} catch (Exception error) {
			Log.d(DEBUG_TAG,
					"Error starting mPreviewLayout: " + error.getMessage());
		}
	}

	public void setCameraDisplayOrientationAndSize() {
		CameraInfo info = new CameraInfo();
		Camera.getCameraInfo(cameraId, info);
		WindowManager systemService = (WindowManager) getContext()
				.getApplicationContext().getSystemService(
						Context.WINDOW_SERVICE);
		int rotation = systemService.getDefaultDisplay().getRotation();
		int degrees = rotation * 90;

		/*
		 * // the above is just a shorter way of doing this, but could break if
		 * the values change switch (rotation) { case Surface.ROTATION_0:
		 * degrees = 0; break; case Surface.ROTATION_90: degrees = 90; break;
		 * case Surface.ROTATION_180: degrees = 180; break; case
		 * Surface.ROTATION_270: degrees = 270; break; }
		 */

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360;
		} else {
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);

		Camera.Size previewSize = camera.getParameters().getPreviewSize();
		if (result == 90 || result == 270) {
			// swap - the physical camera itself doesn't rotate in relation
			// to the screen ;)
			holder.setFixedSize(previewSize.height, previewSize.width);
		} else {
			holder.setFixedSize(previewSize.width, previewSize.height);

		}
	}
}