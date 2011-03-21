package com.linkesoft.apfel2;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Zweite Version des Apfelmännchens, demonstriert
 * <ul> 
 * <li>Hintergrund-Thread und SurfaceView</li>
 * <li>Interaktion mit UI-Thread über Handler</li>
 * <li>Definieren eigener Attribute</li>
 * <li>einfaches Touch-Event</li>
 * </ul>
 * @author Andreas Linke
 */
public class ApfelView extends SurfaceView implements SurfaceHolder.Callback {

	// Zentrum und Breite des Apfelmännchens
	float xcenter = -0.5f;
	float ycenter = 0;
	float xwidth = 3;
	
	private UpdateThread updateThread; // Hintergrund-Thread zum Aktualisieren der Grafik
	private final int timerLabelID; // Resource ID des TextViews zur Anzeige der verbrauchten Rechenzeit
	
	public ApfelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
		// auslesen des eigenen Attributs app:timerLabel 
		// (definiert in res/values/attrs.xml)
       TypedArray attrArray = context.obtainStyledAttributes(attrs,R.styleable.ApfelView);
	   timerLabelID=attrArray.getResourceId(R.styleable.ApfelView_timerLabel, 0);
       attrArray.recycle(); // wichtig, Attribute müssen wieder freigegeben werden      
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Verschiebe Mittelpunkt auf Berührungspunkt
			xcenter += (event.getX() / getWidth() - 0.5f) * xwidth;
			ycenter += (event.getY() / getHeight() - 0.5f) * xwidth * getHeight() / getWidth();
			zoom(true);
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	
	public void zoom(boolean zoomIn) {
		if(zoomIn) {
			xwidth*=0.5;
			}
		else {			
			xwidth*=2;
		}
		restartUpdateThread(); // Neuberechnung	
	}

	private void restartUpdateThread() {
		// stoppe aktuell laufenden Thread, falls vorhanden
		if (updateThread != null)
			updateThread.stopUpdate(); // wartet, bis Thread beendet ist
		// erzeuge neuen Thread
		updateThread = new UpdateThread(this);
		// übergib Handler für das Aktualisieren des Timer-Views
		if (timerLabelID != 0) {			
			updateThread.setUpdateTimerLabelHandler(new Handler() {
				@Override
				public void handleMessage(Message msg) {
					TextView timerLabel = (TextView) getRootView().findViewById(timerLabelID);
					timerLabel.setText((String) msg.obj);
				}
			});
		}
		updateThread.start(); // führe run() Methode aus
	}

	// SurfaceHolder.Callback Routinen
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		restartUpdateThread();		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// Thread wird in surfaceChanged gestartet		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		updateThread.stopUpdate();		
		updateThread=null;
	}		
	
}
