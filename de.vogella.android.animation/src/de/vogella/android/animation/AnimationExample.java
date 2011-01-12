package de.vogella.android.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationExample extends Activity implements AnimationListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void startAnimation(View view) {
		Animation animation = AnimationUtils.makeOutAnimation(this, false);
		animation.setAnimationListener(this);
		View animatedView = findViewById(R.id.textview);
		animatedView.startAnimation(animation);

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