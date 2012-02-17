package de.vogella.camera.api;

import java.io.IOException;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;

public class TextureViewActivity extends Activity implements
		TextureView.SurfaceTextureListener {
	private Camera mCamera;
	private TextureView mTextureView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mTextureView = new TextureView(this);
		mTextureView.setSurfaceTextureListener(this);

		setContentView(mTextureView);
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width,
			int height) {
		mCamera = Camera.open(findFirstFrontFacingCamera());

		Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
		mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
				previewSize.width, previewSize.height, Gravity.CENTER));

		try {
			mCamera.setPreviewTexture(surface);
		} catch (IOException t) {
		}

		mCamera.startPreview();

		mTextureView.setAlpha(0.5f);
		mTextureView.setRotation(45.0f);
	}

	private int findFirstFrontFacingCamera() {
		int cameraId = -1;
		// search for the first front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				break;
			}
		}
		return cameraId;
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width,
			int height) {
		// Ignored, the Camera does all the work for us
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		mCamera.stopPreview();
		mCamera.release();
		return true;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		// Called whenever a new frame is available and displayed in the
		// TextureView
	}
}