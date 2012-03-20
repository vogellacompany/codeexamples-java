package de.vogella.android.userinterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
	private final Paint shinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Path shinePath;

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.drawable.background);
	}

	private void createShinePath() {
		int width = getWidth();
		int height = (int) (0.85 * getHeight());
		shinePath = new Path();
		shinePath.moveTo(0, 0);
		shinePath.lineTo(width, 0);
		shinePath.lineTo(width, height);
		shinePath.close();
		shinePaint.setShader(new LinearGradient(0, 0, 0, height, 0x66ffffff,
				0x10ffffff, Shader.TileMode.CLAMP));
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (shinePath == null) {
			createShinePath();
		}
		// Draw the shine behind the children.
		canvas.drawPath(shinePath, shinePaint);
		// Draw the children.
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// Invalidate the path whenever the size changes.
		shinePath = null;
	}

}
