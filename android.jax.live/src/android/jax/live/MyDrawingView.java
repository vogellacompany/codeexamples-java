package android.jax.live;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawingView extends View {

	private Path path;
	private Paint paint;
	private Bitmap bitmap;
	private Bitmap resizedBitmap;

	public MyDrawingView(Context context) {
		super(context);
		path = MyAppliction.path;
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(10f);

		AssetManager manager = context.getAssets();

		InputStream stream = null;
		try {
			stream = manager.open("fire.png");
			bitmap = BitmapFactory.decodeStream(stream);
		} catch (IOException e) {
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

	protected void onDraw(Canvas canvas) {
		if (bitmap != null && resizedBitmap == null) {
			resizedBitmap = resizeBitmap(bitmap);
		}
		if (resizedBitmap != null) {
			canvas.drawBitmap(resizedBitmap, new Matrix(), null);
		}
		canvas.drawPath(path, paint);
	};

	private Bitmap resizeBitmap(Bitmap source) {
		int measuredHeight = getMeasuredHeight();
		int measuredWidth = getMeasuredWidth();
		int height = source.getHeight();
		int width = source.getWidth();
		float s1 = getScale(width, measuredWidth);
		float s2 = getScale(height, measuredHeight);
		Matrix matrix = new Matrix();
		matrix.setScale(s1, s2);
		Bitmap createBitmap = Bitmap.createBitmap(source, 0, 0, width, height,
				matrix, true);
		return createBitmap;
	}

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

	@Override
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

	public void setBitmap(Bitmap input) {
		if (resizedBitmap != null) {
			resizedBitmap.recycle();
			resizedBitmap = null;
		}
		this.bitmap = input;
		invalidate();
	}
}
