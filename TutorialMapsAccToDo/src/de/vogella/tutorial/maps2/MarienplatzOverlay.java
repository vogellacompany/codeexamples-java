package de.vogella.tutorial.maps2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MarienplatzOverlay extends Overlay {
	private GeoPoint geoPointMarienplatz;
	private GeoPoint geoPointCurrent;
	private Bitmap bitmap;
	private Paint paintLine;

	public MarienplatzOverlay(Activity activity, GeoPoint geoPointMarienplatz) {
		this.geoPointMarienplatz = geoPointMarienplatz;
		bitmap = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.munich);
		paintLine = new Paint();
		paintLine.setAntiAlias(true);
		paintLine.setColor(0xaa0000aa);
		paintLine.setStrokeWidth(3);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (shadow) {
			return;
		}
		Projection projection = mapView.getProjection();
		Point point = new Point();
		projection.toPixels(geoPointMarienplatz, point);
		canvas.drawBitmap(bitmap, point.x, point.y, null);
		if (geoPointCurrent != null) {
			Point pointCurrent = new Point();
			projection.toPixels(geoPointCurrent, pointCurrent);
			canvas.drawLine(point.x, point.y, pointCurrent.x, pointCurrent.y,
					paintLine);
		}
	}

	public void setGeoPointCurrent(GeoPoint geoPointCurrent) {
		this.geoPointCurrent = geoPointCurrent;
	}

}
