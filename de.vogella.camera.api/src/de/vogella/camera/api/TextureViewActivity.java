package de.vogella.camera.api;

import java.io.IOException;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

public class TextureViewActivity extends Activity implements
		TextureView.SurfaceTextureListener {
	private Camera camera;
	private TextureView mTextureView;
	private float rotation = 0;

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
		camera = Camera.open(findFirstFrontFacingCamera());

		Camera.Size previewSize = camera.getParameters().getPreviewSize();
		mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
				previewSize.width, previewSize.height, Gravity.CENTER));

		try {
			camera.setPreviewTexture(surface);
		} catch (IOException t) {
		}

		camera.startPreview();

		mTextureView.setAlpha(0.8f);
		mTextureView.setRotation(45.0f);

	}

	public void onClick(View view) {
		camera.takePicture(null, null,
				new PhotoHandler(getApplicationContext()));
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
		camera.stopPreview();
		camera.release();
		return true;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		// Called whenever a new frame is available and displayed in the
		// TextureView
		rotation += 1.0f;
		if (rotation > 360) {
			rotation = 0;
		}
		mTextureView.setRotation(rotation);
	}
}