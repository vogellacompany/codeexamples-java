package com.linkesoft.apfel1;

import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * Erste Version des Apfelm�nnchens, demonstriert
 * <ul> 
 * <li>Eigener View mit �berschriebener {@code onDraw()}-Methode</li>
 * <li>Arbeit mit Canvas und Paint</li>
 * </ul>
 * @author Andreas Linke
 *
 */
public class ApfelView extends View {

	private final static float MAXQ = 4f; // Abbruchkriterium (Quadrat von z)
	private final static int MAXITER = 100; // maximale Anzahl der Iterationen
	private static final int NUMX =100; // Anzahl der horizontalen Pixel 

	// Zentrum und Breite des Apfelm�nnchens
	private final float xcenter = -0.5f;
	private final float ycenter = 0;
	private final float xwidth = 3;
	// Farbtabell f�r Iterationswerte
	private final int[] colormap = new int[MAXITER];

	public ApfelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		fillColorMap();
	}
/**
 * Einfacher Mandelbrot-Algorithmus, 
 * messe Divergenz von z(i+1)=z(i)^2+c 
 * wobei z und c komplexe Zahlen. 
 * <p>
 * @see <a href="http://de.wikipedia.org/wiki/Mandelbrot-Menge">Mandelbrot-Menge (Wikipedia)</a> 
 * @param xp Real-Teil von c
 * @param yp Imagin�r-Teil von c
 * @return Farbwert f�r Punkt
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
		// iter ist 1..MAXITER
		return colormap[iter - 1];
	}
/**
 * Erzeuge Farbtabelle
 */
	private void fillColorMap() {
		for (int i = 0; i < MAXITER; i++) {
			float f = (float) i / (float) MAXITER;
			// Mandelbrot Coloring nach Iain Parris 
			// au�en schwarz, innen wei�, dazwischen kontinuierlicher Farbverlauf
			int g = (int)(f*256);
			int r=(f<0.2?(int)(f*5*256):255);
			int b = (int)((Math.sin(f*3.5*2*Math.PI-Math.PI/2)+1)/2*256);
			colormap[i] = Color.rgb(r, g, b);
			// Alternative: einfaches HSV Coloring
			/*
			float hsv[]=new float[3];
			hsv[0]=f*240f;
			hsv[1]=1;
			hsv[2]=1;
			colormap[i]= Color.HSVToColor(hsv);
			*/			
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// mit isInEditMode() kann festgestellt werden, ob onDraw 
		// in der IDE oder auf einem richtigen Android-Ger�t/Emulator ausgef�hrt wird
		// f�r die IDE zeichnen wir eine gr�bere (schnellere) Version
		if(isInEditMode())
			drawApfelm(canvas, 20,20*getHeight()/getWidth());
		else
			drawApfelm(canvas, NUMX,NUMX*getHeight()/getWidth());
	}
/**
 * Zeichne Apfelm�nnchen auf die Leinwand mit kleinen Rechtecken als Pixel
 * @param canvas Leinwand zum Zeichnen
 * @param numX Anzahl der horizontalen "Pixel" 
 * @param numY Anzahl der vertikalen "Pixel"
 */
	private void drawApfelm(Canvas canvas,int numX,int numY) {
		long t=SystemClock.currentThreadTimeMillis();
		float d=xwidth/numX; // Abstand der Punkte in der komplexen Ebene
		float pixelSize=((float)getWidth())/numX; // quadratisch
		float y=ycenter - 0.5f * xwidth*getHeight()/getWidth();
		RectF rect = new RectF();
		Paint paint = new Paint();
		for(int iy=0;iy<numY;iy++)
		{
			float x=xcenter - 0.5f * xwidth;;
			for(int ix=0;ix<numX;ix++)
			{
				x+=d;
				int color = colorForPoint(x, y);
				paint.setColor(color);				
				rect.set(ix*pixelSize, iy*pixelSize, (ix+1)*pixelSize,(iy+1)*pixelSize);
				canvas.drawRect(rect, paint);
			}
			y+=d;			
		}		
		t=SystemClock.currentThreadTimeMillis()-t;		
		// schreibe ben�tigte Zeit auf die Leinwand
		drawUsedTime(canvas,t,numX*numY);
	}
/**
 * Schreibe ben�tigte Rechenzeit in rechte untere Ecke
 * @param canvas Leinwand zum Schreiben
 * @param t Zeit in ms
 * @param count Gesamtzahl der Pixel
 */
	private void drawUsedTime(Canvas canvas, long t, int count) {
		// formatiere Text-String
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		String totals = f.format((double) t / 1000);
		f.setMaximumFractionDigits(4);
		String msperpixel = f.format((double) t / count);
		String timestr = "Total " + totals + " s, " + msperpixel + " ms/pixel";
		// zeichne auf den Bildschirm
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextAlign(Align.RIGHT);
		paint.setColor(Color.LTGRAY);
		canvas.drawText(timestr, getWidth(), getHeight(), paint);
	}
}
