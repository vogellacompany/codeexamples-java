package com.linkesoft.apfel3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ZoomButtonsController;
import android.widget.ZoomButtonsController.OnZoomListener;

/**
 * Dritte Version des Apfelmännchens, demonstriert
 * <ul> 
 * <li>Verwendung des Bitmap-Objekts</li>
 * <li>JNI im UpdateThread</li>
 * <li>ZoomButtonsController</li>
 * <li>Verschiedene Touch-Events</li>
 * <li>AnimationSet</li>
 * </ul>
 * @author Andreas Linke
 */
public class ApfelView extends View {

	// Zentrum und Breite des Apfelmännchens
	float xcenter = -0.5f;
	float ycenter = 0;
	float xwidth = 3;
	
	private final PointF startPoint=new PointF(); // Startpunkt für Verschieben (bei ACTION_DOWN gesetzt), in View-Koordinaten
	private float xpan,ypan; // Verschiebung in View-Koordinaten
	private Bitmap bitmap; // Bitmap zum Zeichnen auf die Canvas, vom Update-Thread aktualisiert

	private UpdateThread updateThread;
	private final int timerLabelID;
	private final ZoomButtonsController zoomController;
	
	public ApfelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.ApfelView);
		timerLabelID = attrArray.getResourceId(R.styleable.ApfelView_timerLabel, 0);
		attrArray.recycle();
		// Zoom Buttons Controller (zunächst unsichtbar, wird erst bei Touch aktiviert)
		zoomController = new ZoomButtonsController(this);
		zoomController.setOnZoomListener(new OnZoomListener() {
			@Override
			public void onZoom(boolean zoomIn) {
				zoom(zoomIn);
			}
			@Override
			public void onVisibilityChanged(boolean visible) {
			}
		});
		xpan=0;
		ypan=0;
		// lade initial das Programm-Icon als Bitmap 
		// bis der Update-Thread das erste Bild berechnet hat
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		// das Layout (Breite/Höhe des Views) steht fest, starte Update-Thread
		restartUpdateThread();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(xpan!=0||ypan!=0)
		{
			// schwarzer Hintergrund, falls Bitmap verschoben
			Paint paint=new Paint();
			paint.setColor(Color.BLACK);
			paint.setStyle(Style.FILL);
			canvas.drawRect(new Rect(0,0,getWidth(),getHeight()), paint);
		}
		// zeichne Bitmap, ggf. skaliert auf volle View-Größe und verschiebe entsprechend
		canvas.drawBitmap(bitmap, null, new RectF(xpan,ypan, xpan+getWidth(), ypan+getHeight()), null);			
		super.onDraw(canvas);
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// speichere Startpunkt (für Zoom, Verschieben)
			startPoint.x = event.getX(); 
			startPoint.y = event.getY();
			zoomController.setVisible(true);
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// panning (Verschieben)
			xpan = event.getX() - startPoint.x;
			ypan = event.getY() - startPoint.y;
			invalidate();// zeichne Bitmap an neuer Position (ohne Neuberechnung )
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			// setze neue Position und starte Berechnung
			xcenter += (startPoint.x - event.getX()) / getWidth() * xwidth;
			ycenter += (startPoint.y - event.getY()) / getHeight() * xwidth * getHeight() / getWidth();
			startPoint.x=event.getX();
			startPoint.y=event.getY();
			xpan = ypan = 0;
			restartUpdateThread();
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	private void restartUpdateThread() {
		if (updateThread != null)
			updateThread.stopUpdate();
		updateThread = new UpdateThread(xcenter,ycenter,xwidth, getWidth(), getHeight());
		// setze Handler für Timer-Label
		if (timerLabelID != 0) {
			updateThread.setUpdateTimerLabelHandler(new Handler() {
				@Override
				public void handleMessage(Message msg) {
					TextView timerLabel = (TextView) getRootView().findViewById(timerLabelID);
					timerLabel.setText((String) msg.obj);
				}
			});
		}
		// und Handler zum Update des Views
		updateThread.setRefreshHandler(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				clearAnimation(); // falls noch eine Zoom-Animation läuft, wird sie beendet
				ApfelView.this.bitmap = (Bitmap) msg.obj;
				invalidate(); // View soll neu gezeichnet werden (in onDraw)
			}
		});
		updateThread.start();
	}

	// zoome mit Animation
	public void zoom(boolean zoomIn) {
		// mache startPoint zum neuen Mittelpunkt
		xcenter+=(startPoint.x/getWidth()-0.5f)*xwidth;
		ycenter+=(startPoint.y/getHeight()-0.5f)*xwidth*getHeight()/getWidth();
		float zoomFactor=(zoomIn?2f:0.5f);
		// animiere zoom an neue Position mit Verschiebung und Skalierung
		AnimationSet animationSet=new AnimationSet(false);
		Animation scaleAnimation = new ScaleAnimation(1f,zoomFactor, 1f,zoomFactor,startPoint.x,startPoint.y);
		scaleAnimation.setDuration(500);
		animationSet.addAnimation(scaleAnimation);
		Animation translateAnimation = new TranslateAnimation(0,getWidth()/2-startPoint.x,0,getHeight()/2-startPoint.y);
		translateAnimation.setDuration(500);
		animationSet.addAnimation(translateAnimation);
		startAnimation(animationSet);
		xwidth/=zoomFactor;
		restartUpdateThread(); // starte Neuberechnung	
		// setze startPoint auf Mittelpunkt für wiederholtes Zoomen
		startPoint.x=getWidth()/2;
		startPoint.y=getHeight()/2;
	}
}
