package de.vogella.android.wallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class MyWallpaperService extends WallpaperService {
	private final Handler handler = new Handler();

	@Override
	public Engine onCreateEngine() {

		return new MyWallpaperEngine();

	}

	private class MyWallpaperEngine extends Engine {
		private Paint paint = new Paint();

		public MyWallpaperEngine() {
			paint.setAntiAlias(true);
			paint.setColor(Color.WHITE);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(10f);
			draw();
		}

		private final Runnable drawRunner = new Runnable() {
			@Override
			public void run() {
				draw();
			}

		};

		private void draw() {

			SurfaceHolder holder = getSurfaceHolder();

			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					canvas.drawCircle(100.0f, 100.0f, 50.0f, paint);

				}
			} finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}
			handler.removeCallbacks(drawRunner);
			handler.postDelayed(drawRunner, 1000 / 10);
		}
	}

}
