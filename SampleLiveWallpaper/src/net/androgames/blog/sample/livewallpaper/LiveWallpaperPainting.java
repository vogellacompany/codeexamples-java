package net.androgames.blog.sample.livewallpaper;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * 
 * A sample class that handle the painting of an Android LiveWallpaper 
 * in a proper manner, without consumming resources when there is no need 
 * to modifiy the wallpaper appearence :
 * - when the wallpaper is hidden (no more visible)
 * - when there is nothing to update / animate
 * 
 * Sample from <a href="http://blog.androgames.net">Androgames tutorials blog</a>
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 *
 */
public class LiveWallpaperPainting extends Thread {

	/** Reference to the View and the context */
	private SurfaceHolder surfaceHolder;
	private Context context;
	
	/** State */
	private boolean wait;
	private boolean run;
	
	/** Dimensions */
	private int width;
	private int height;
	private int radius;
	
	/** Touch points */
	private List<TouchPoint> points;
	
	/** Time tracking */
	private long previousTime;
	
	public LiveWallpaperPainting(SurfaceHolder surfaceHolder, Context context, int radius) {
		// keep a reference of the context and the surface
		// the context is needed is you want to inflate
		// some resources from your livewallpaper .apk
		this.surfaceHolder = surfaceHolder;
		this.context = context;
		// don't animate until surface is created and displayed
		this.wait = true;
		// initialize touch paints
		this.points = new ArrayList<TouchPoint>();
		// initialize radius
		this.radius = radius;
	}
	
	/**
	 * Change the radius preference
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Pauses the livewallpaper animation
	 */
	public void pausePainting() {
		this.wait = true;
		synchronized(this) {
			this.notify();
		}
	}

	/**
	 * Resume the livewallpaper animation
	 */
	public void resumePainting() {
		this.wait = false;
		synchronized(this) {
			this.notify();
		}
	}

	/**
	 * Stop the livewallpaper animation
	 */
	public void stopPainting() {
		this.run = false;
		synchronized(this) {
			this.notify();
		}
	}

	@Override
	public void run() {
		this.run = true;
		Canvas c = null;
		while (run) {
			try {
				c = this.surfaceHolder.lockCanvas(null);
				synchronized (this.surfaceHolder) {
					doDraw(c);
				}
			} finally {
				if (c != null) {
					this.surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			// pause if no need to animate
			synchronized (this) {
				if (wait) {
					try {
						wait();
					} catch (Exception e) {}
				}
			}
		}
	}

	/**
	 * Invoke when the surface dimension change
	 * 
	 * @param width
	 * @param height
	 */
	public void setSurfaceSize(int width, int height) {
		this.width = width;
		this.height = height;
		synchronized(this) {
			this.notify();
		}
	}
	
	/**
	 * Invoke while the screen is touched
	 * 
	 * @param event
	 */
	public void doTouchEvent(MotionEvent event) {
		synchronized(this.points) {
			points.add(new TouchPoint(
					(int) event.getX(), 
					(int) event.getY(),
					0xFFFFFFFF, 
					Math.min(width, height) / this.radius));
		}
		this.wait = false;
		synchronized(this) {
			notify();
		}
	}

	/**
	 * Do the actual drawing stuff
	 * 
	 * @param canvas
	 */
	private void doDraw(Canvas canvas) {
		long currentTime = System.currentTimeMillis();
		long elapsed = currentTime - previousTime;
		if (elapsed > 20) {
			// clear background
			canvas.drawColor(0xFF000000);
			// draw touch points
			Paint paint = new Paint();
			List<TouchPoint> pointsToRemove = new ArrayList<TouchPoint>();
			synchronized(this.points) {
				for (TouchPoint point : points) {
					paint.setColor(point.color);
					point.radius -= elapsed / 20;
					if (point.radius <= 0) {
						pointsToRemove.add(point);
					} else {
						canvas.drawCircle(point.x, point.y, point.radius, paint);
					}
				}
				points.removeAll(pointsToRemove);
			}
			previousTime = currentTime;
			if (points.size() == 0) {
				wait = true;
			}
		}
	}
	
	class TouchPoint {
		
		int x;
		int y;
		int color;
		int radius;
		
		public TouchPoint(int x, int y, int color, int radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.color = color;
		}
		
	}
	
}
