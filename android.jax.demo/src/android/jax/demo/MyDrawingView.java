package android.jax.demo;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawingView extends View {

	private Path path;
	private Paint paint;
	private Bitmap bitmap;
	private Bitmap renderedBitMap;

	public MyDrawingView(Context context) {
		super(context);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(10f);
		path = MyApplication.path;
		// Lade default Bitmap
		AssetManager assets = context.getAssets();
		try {
			InputStream open = assets.open("fire.png");
			bitmap = BitmapFactory.decodeStream(open);
			// TODO MOVE TO FINALLY!!!
			open.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MyDrawingView(Context context, AttributeSet attrs, int defStyle) {
		this(context);
	}

	public MyDrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (renderedBitMap == null && bitmap != null) {
			renderedBitMap = calculatedBitMap(bitmap);
		}

		if (renderedBitMap != null) {
			canvas.drawBitmap(renderedBitMap, new Matrix(), null);
		}

		canvas.drawPath(path, paint);
	}

	private Bitmap calculatedBitMap(Bitmap localBitmap) {
		// Set the background to something interesting
		int height = getMeasuredHeight();
		int width = getMeasuredWidth();
		int w = localBitmap.getWidth();
		int h = localBitmap.getHeight();
		float s1 = getScale(w, width);
		float s2 = getScale(h, height);

		if (BuildConfig.DEBUG) {
			Log.w("TEST", "Height" + String.valueOf(height));
			Log.w("TEST", String.valueOf(h));
			Log.w("TEST", String.valueOf(width));
			Log.w("TEST", String.valueOf(w));
			Log.w("TEST", String.valueOf(s1));
			Log.w("TEST", String.valueOf(s2));
		}
		Matrix matrix = new Matrix();
		matrix.setScale(s1, s2);

		Camera camera = new Camera();
		camera.save();
		camera.rotate(10, 40, 40);
		camera.getMatrix(matrix);
		camera.restore();
		Bitmap bitmap = Bitmap.createBitmap(localBitmap, 0, 0, w, h, matrix,
				true);
		return bitmap;
	}

	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			invalidate();
			break;
		default:
			break;
		}
		return true;
	}

	public void setBitmap(Bitmap bitmap) {
		Log.e("TEST", "CALLED");
		renderedBitMap.recycle();
		renderedBitMap = null;
		this.bitmap = bitmap;
		invalidate();
	};

	public float getScale(int BitMapSize, int ScreenSize) {
		float scale = 1;
		if (BitMapSize < ScreenSize) {
			// Upscale
			scale = (float) Math.max(BitMapSize, ScreenSize)
					/ (float) Math.min(BitMapSize, ScreenSize);
		} else {
			// DownScale
			scale = (float) Math.min(BitMapSize, ScreenSize)
					/ (float) Math.max(BitMapSize, ScreenSize);
		}
		return scale;
	}

	public void setDrawingColor(int color) {
		paint.setColor(color);
		invalidate();
	}

	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (renderedBitMap != null) {
			renderedBitMap.recycle();
			renderedBitMap = null;
		}
	};
}
