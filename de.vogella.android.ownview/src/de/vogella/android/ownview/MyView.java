package de.vogella.android.ownview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context) {
		super(context);

	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// Calculate the size given certain boundery conditions
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = getMeasuredWidth(widthMeasureSpec);
		int measuredHeight = getMeasuredHeight(heightMeasureSpec);
		// Must be set
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	private int getMeasuredWidth(int widthMeasureSpec) {
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		// Default result
		int result = 400;

		// Set to Maximum or to the bounderies of the view container
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
			result = size;
		}
		return result;
	}
	private int getMeasuredHeight(int heightMeasureSpec) {
		int mode = MeasureSpec.getMode(heightMeasureSpec);
		int size = MeasureSpec.getSize(heightMeasureSpec);
		// Default result
		int result = 400;
		
		// Set to Maximum or to the bounderies of the view container
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
			result = size;
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Lets get the current size of the view
		int measuredHeight = getMeasuredHeight();
		int measuredWidth = getMeasuredWidth();
		// We need something to paint with
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		paint.setStrokeWidth(10);
		String text = "Hello my own View";
		float measureTextCenter = paint.measureText(text) / 2;
		canvas.drawText(text, measuredWidth / 2 - measureTextCenter,
				measuredHeight / 2, paint);
		super.onDraw(canvas);
	}

}
