package com.linkesoft.apfel2;

import java.text.DecimalFormat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Hintergrund-Thread zum Aktualisieren eines SurfaceViews mit der Apfelmännchengrafik in verschiedenen Auflösungen.
 * @author Andreas Linke
 *
 */
public class UpdateThread extends Thread {
	private final static float MAXQ = 4f;
	private final static int MAXITER = 100;

	private final int[] colormap = new int[MAXITER];

	private volatile boolean stop = false; // true um Berechnung abzubrechen und Thread zu beenden
	private Handler updateTimerLabelHandler; // Handler zum Aktualiseren des TextViews für Rechenzeitanzeige
	private final ApfelView apfelView;

	public UpdateThread(ApfelView apfelView) {
		super("Apfelm Calculations"); // setze Thread-Namen
		this.apfelView = apfelView;
		fillColorMap();
	}

	@Override
	public void run() {
		// zeichne mehrfach mit immer weiter erhöhter Genauigkeit
		for (int level = 16; level >= 1; level /= 2) {
			Canvas canvas = null;
			try {
				canvas = apfelView.getHolder().lockCanvas();
				drawApfelm(canvas, apfelView.getWidth() / level,apfelView.getHeight()/level);
			} finally {
				if (canvas != null)
					apfelView.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}

	private void drawApfelm(Canvas canvas, int numX,int numY) {
		if (updateTimerLabelHandler != null) {
			// aktualisiere TextView über Handler
			Message msg = Message.obtain();
			msg.obj = "Calculating";
			updateTimerLabelHandler.sendMessage(msg);
		}
		long t = SystemClock.currentThreadTimeMillis(); // Startzeitpunkt
		float d=apfelView.xwidth/numX; // Abstand der Punkte in der komplexen Ebene
		float pixelSize=((float)apfelView.getWidth())/numX; // quadratisch
		float y=apfelView.ycenter - 0.5f * apfelView.xwidth*apfelView.getHeight()/apfelView.getWidth();
		RectF rect = new RectF();
		Paint paint = new Paint();
		for (int iy = 0; iy < numY && !stop; iy++) {
			float x = apfelView.xcenter - 0.5f * apfelView.xwidth;
			for (int ix = 0; ix < numX && !stop; ix++) {
				x += d;
				int color = colorForPoint(x, y);
				paint.setColor(color);
				rect.set(ix * pixelSize, iy * pixelSize, (ix + 1) * pixelSize, (iy + 1) * pixelSize);
				canvas.drawRect(rect, paint);
			}
			y += d;
		}
		t = SystemClock.currentThreadTimeMillis() - t;
		// gib benötigte Zeit aus
		if (!stop)
			drawUsedTime(t, numX*numY);
	}

	private void drawUsedTime(long t, int count) {
		// formatiere Text-String
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		String totals = f.format((double) t / 1000);
		f.setMaximumFractionDigits(4);
		String msperpixel = f.format((double) t / count);
		String timestr = "Total " + totals + " s\n" + msperpixel + " ms/pixel";
		// aktualisiere TextView über Handler
		if (updateTimerLabelHandler != null) {
			Message msg = Message.obtain();
			msg.obj = timestr;
			updateTimerLabelHandler.sendMessage(msg);
		}
	}

	public void setUpdateTimerLabelHandler(Handler updateTimerLabelHandler) {
		this.updateTimerLabelHandler = updateTimerLabelHandler;
	}

	public void stopUpdate() {
		stop = true;
		while (true) {
			try {
				// diese Methode wird aus einem anderen Thread aufgrufen
				// der aufrufende Thread wartet, bis der Update-Thread beendet ist
				// d.h. seine run()-Methode verlassen hat
				this.join();
				break;
			} catch (InterruptedException e) {
				; // ignorieren und neu versuchen
			}
		}
	}
	private void fillColorMap() {
		for (int i = 0; i < MAXITER; i++) {
			// Mandelbrot Coloring nach Iain Parris
			// außen schwarz, innen weiß, dazwischen kontinuierlicher Farbverlauf			
			float f = i / (float) MAXITER;
			int g = (int) (f * 256);
			int r = (f < 0.2 ? (int) (f * 5 * 256) : 255);
			int b = (int) ((Math.sin(f * 3.5 * 2 * Math.PI - Math.PI / 2) + 1) / 2 * 256);
			colormap[i] = Color.rgb(r, g, b);
		}
	}
	/**
	 * Einfacher Mandelbrot-Algorithmus, 
	 * messe Divergenz von z(i+1)=z(i)^2+c 
	 * wobei z und c komplexe Zahlen. 
	 * <p>
	 * @see <a href="http://de.wikipedia.org/wiki/Mandelbrot-Menge">Mandelbrot-Menge (Wikipedia)</a> 
	 * @param xp Real-Teil von c
	 * @param yp Imaginär-Teil von c
	 * @return Farbwert für Punkt
	 */
		private final int colorForPoint(final float xp, final float yp) {
			float x = 0; // real(z)
			float y = 0; // imag(z)
			int iter = 0;
			float x2=0; // x^2
			float y2=0; // y^2
			do {
				y=2*x*y+yp;
				x=x2-y2+xp;
				x2=x*x;
				y2=y*y;
				iter++;
			} while (x2+y2 < MAXQ && iter < MAXITER);
			return colormap[iter - 1];
		}
		
	// Integer-Implementierung zum Vergleich
	/*
	    final private int BITS=24;
		final private int FIXED_ONE=(1<<BITS); 
		private final int colorForPoint(float xp, float yp) {
			int x = 0; // real(z)
			int y = 0; // imag(z)
			int iter = 0;
			int x2=0; // x^2
			int y2=0; // y^2
			int ixp=(int)(xp*FIXED_ONE);
			int iyp=(int)(yp*FIXED_ONE);
			int maxq=((int)MAXQ)<<BITS;
			do {
				y=(int)(((long)x*y)>>BITS);
				y<<=1;
				y+=iyp;
				x=x2-y2+ixp;
				x2=(int)(((long)x*x)>>BITS);
				y2=(int)(((long)y*y)>>BITS);
				iter++;
			} while (x2+y2 < maxq && iter < MAXITER);
			return colormap[iter - 1];
		}*/
}
