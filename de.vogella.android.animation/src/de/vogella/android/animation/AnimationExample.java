package de.vogella.android.animation;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class AnimationExample extends Activity implements AnimationListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void startAnimation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			Animation animation1 = AnimationUtils.loadAnimation(this,
					R.anim.myanimation);
			animation1.setAnimationListener(this);
			View animatedView1 = findViewById(R.id.rotatetext);
			animatedView1.startAnimation(animation1);
			break;

		case R.id.Button02:
			// Get the window manager
//			Display display = getWindowManager().getDefaultDisplay();
//			int width = display.getWidth();
	        Paint paint = new Paint();
	        TextView animatedView2 = (TextView) findViewById(R.id.scrolltext);
	        float measureTextCenter = paint.measureText(animatedView2.getText().toString());
	        Animation animation2 = new TranslateAnimation(0f, -measureTextCenter, 0.0f, 0.0f);
	        animation2.setDuration(2000);
//	        animatedView.setAnimation(animation2); 
	        animatedView2.startAnimation(animation2);
			break;

		default:
			break;
		}

	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Animation inAnimation = AnimationUtils.makeInAnimation(this, false);
		Button button = new Button(this);
		button.setText("This is the new view");
		button.setAnimation(inAnimation);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}
}