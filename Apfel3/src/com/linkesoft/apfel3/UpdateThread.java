package com.linkesoft.apfel3;

import java.text.DecimalFormat;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Hintergrund-Thread zur Berechnung der Apfelmännchen-Bitmap in verschiedenen Auflösungen. 
 * Die fertige Bitmap wird über einen Handler an den View übergeben, der sie dann in der onDraw-Methode zeichnet.
 * @author Andreas Linke
 */
public class UpdateThread extends Thread {
	private final static int MAXITER = 100;
	// Zentrum und Breite des Apfelmännchens
	private final float xcenter; 
	private final float ycenter;
	private final float xwidth;

	private final int width; // View-Größe
	private final int height;
	private final int STRIPHEIGHT=100; // wir berechnen in Streifen, um Speicher zu sparen und bessere Response zu erhalten

	private final int[] colormap = new int[MAXITER]; // Farbtabelle
	private final int pixels[]; // im C-Programm berechnete Pixel (Farbwerte), Größe width*STRIPHEIGHT

	private volatile boolean stop = false;
	private Handler updateTimerLabelHandler;
	private Handler refreshHandler;
	
	// JNI lade libfract.so mit C-Funktionen
	static {
        System.loadLibrary("fract");
    }
	// deklariere native C-Funktion Java_com_linkesoft_apfel3_UpdateThread_iteratePixelsJNI
    private native static void iteratePixelsJNI(int pixels[],
    		int colormap[],int maxiter,int w,int h,
    		float xmin,float ymin,float xmax,float ymax);

	public UpdateThread(float xcenter,float ycenter,float xwidth, int width, int height) {
		super("Apfelm Calculations"); 
		this.xcenter = xcenter;
		this.ycenter = ycenter;
		this.xwidth = xwidth;
		this.width = width;
		this.height = height;
		pixels=new int[width*STRIPHEIGHT];
		fillColorMap();		
	}

	@Override
	public void run() {
		// zeichne mehrfach mit immer weiter erhöhter Genauigkeit
		// Bitmap ist am Anfang deutlich kleiner als die View-Größe
		// und wird im View skaliert
		 for (int level = 4; level >= 1; level /= 2) {
			int numX = width/ level;
			int numY = (int)(numX * (float)height/width);
			calcApfelm(numX, numY);
		} 
	}	

	private void calcApfelm(int numX, int numY) {
		if (updateTimerLabelHandler != null) {
			Message msg = Message.obtain();
			msg.obj = "Calculating";
			updateTimerLabelHandler.sendMessage(msg);
		}
		long t = SystemClock.currentThreadTimeMillis();
		float xmin = xcenter - 0.5f * xwidth;
		float xmax = xcenter + 0.5f * xwidth;
		float ywidth = xwidth * height / width;
		float ymin = ycenter - 0.5f * ywidth;
		float dy = ywidth / numY;
		// erzeuge neue Bitmap der entsprechenden Größe und 8bit-RGB-Farbtiefe plus Transparenz (hier nicht genutzt)
		Bitmap bitmap = Bitmap.createBitmap(numX, numY, Config.ARGB_8888);
		// fülle Bitmap streifenweise
		for (int y = 0; y < numY && !stop; y += STRIPHEIGHT) {
			int stripY = STRIPHEIGHT;
			if (y + stripY > numY)
				stripY = numY - y; // der letzte Streifen kann schmaler sein
			iteratePixelsJNI(pixels, colormap, MAXITER, numX, stripY, xmin, ymin, xmax, ymin + dy * stripY);
			// setze entsprechenden Pixel in der Bitmap
			bitmap.setPixels(pixels, 0, numX, 0, y, numX, stripY);
			ymin += dy * stripY;
		}
		t = SystemClock.currentThreadTimeMillis() - t;
		if (!stop) {			
			updateUsedTime(t, numX * numY);
			// sende Bitmap zum Update an View
			Message msg = Message.obtain();
			msg.obj = bitmap;
			refreshHandler.sendMessage(msg);
		}
	}

	private void fillColorMap() {
		for (int i = 0; i < MAXITER; i++) {
			// Mandelbrot Coloring nach Iain Parris
			float f = i / (float) MAXITER;
			int g = (int) (f * 256);
			int r = (f < 0.2 ? (int) (f * 5 * 256) : 255);
			int b = (int) ((Math.sin(f * 3.5 * 2 * Math.PI - Math.PI / 2) + 1) / 2 * 256);
			colormap[i] = Color.rgb(r, g, b);
		}
	}

	private void updateUsedTime(long t, int count) {
		// formatiere Text-String
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		String totals = f.format((double) t / 1000);
		f.setMaximumFractionDigits(1);
		String usperpixel = f.format((double) t / count*1000.0);
		String timestr = "Total " + totals + " s\n" + usperpixel + " \u03bcs/pixel"; // mju
		// aktualisiere View
		if (updateTimerLabelHandler != null) {
			Message msg = Message.obtain();
			msg.obj = timestr;
			updateTimerLabelHandler.sendMessage(msg);
		}
	}

	public void setUpdateTimerLabelHandler(Handler updateTimerLabelHandler) {
		this.updateTimerLabelHandler = updateTimerLabelHandler;
	}
	public void setRefreshHandler(Handler refreshHandler) {
		this.refreshHandler = refreshHandler;
	}

	public void stopUpdate() {
		stop = true;
		while (true) {
			try {
				// diese Methode wird aus einem anderen Thread aufgrufen
				// der aufrufende Thread wartet, bis der UpdateThread beendet ist,
				// d.h. die run()-Methode verlassen hat
				this.join();
				break;
			} catch (InterruptedException e) {
				; // ignorieren und neu versuchen
			}
		}
	}

}
