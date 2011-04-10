package android.test2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	Path path;
	Paint paint;

	public DrawingView(Context context) {
		super(context);
		path = new Path();
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			break;
		default:
			break;
		}
		invalidate();
		return true;

	}
}
