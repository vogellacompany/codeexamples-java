package de.vogella.customview;

public class DrawView extends View {

	public DrawView(Context context) {
		super(context);
		initView();
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private Path path = new Path();
	private Paint paint = new Paint();

	private void initView() {
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(10f);
		paint.setAlpha(122);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawARGB(255, 255, 255, 255);
		canvas.drawARGB(112, 255, 0, 0);
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		float x = event.getX();
		float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			break;
		default:
			return super.onTouchEvent(event);
		}

		invalidate();
		return true;
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		// TODO Auto-generated method stub
		return super.onSaveInstanceState();
	}

}
