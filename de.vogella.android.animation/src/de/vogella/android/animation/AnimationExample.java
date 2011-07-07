package de.vogella.android.animation;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class AnimationExample extends Activity implements AnimationListener {
	private TextView animatedView3;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void startAnimation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			// Show how to load an animation from XML
			Animation animation1 = AnimationUtils.loadAnimation(this,
					R.anim.myanimation);
			animation1.setAnimationListener(this);
			View animatedView1 = findViewById(R.id.rotatetext);
			animatedView1.startAnimation(animation1);
			break;

		case R.id.Button02:
			// Shows how to define a animation via code
			// Also use an Interpolator (BounceInterpolator)
			Paint paint = new Paint();
			TextView animatedView2 = (TextView) findViewById(R.id.scrolltext);
			float measureTextCenter = paint.measureText(animatedView2.getText()
					.toString());
			Animation animation2 = new TranslateAnimation(0f,
					-measureTextCenter, 0.0f, 0.0f);
			animation2.setDuration(5000);
			animation2.setInterpolator(new BounceInterpolator());
			animatedView2.startAnimation(animation2);
			break;

		case R.id.Button03:
			// Demonstrate fading and adding an AnimationListener
			animatedView3 = (TextView) findViewById(R.id.fadeout);
			float from = 1.0f;
			float to = 0.0f;

			if (animatedView3.getVisibility() == View.INVISIBLE) {
				from = to;
				to = 1.0f;
			}

			Animation animation3 = new AlphaAnimation(from, to);
			animation3.setDuration(5000);
			animation3.setAnimationListener(this);
			animatedView3.startAnimation(animation3);

			break;

		case R.id.Button04:
			// Demonstrate LayoutAnimation
			ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
			Animation animation4 = new AlphaAnimation(0.0f, 1.0f);
			animation4.setDuration(5000);
			LayoutAnimationController controller = new LayoutAnimationController(
					animation4, 0);
			layout.setLayoutAnimation(controller);

			View button = findViewById(R.id.Button03);
			if (button == null) {
				layout.addView(button);
			} else {
				layout.removeView(button);
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void onAnimationStart(Animation animation) {
		Toast.makeText(this, "Animation started", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Toast.makeText(this, "Animation ended", Toast.LENGTH_SHORT).show();
		if (animatedView3.getVisibility() == View.VISIBLE) {
			animatedView3.setVisibility(View.INVISIBLE);
		} else {
			animatedView3.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Toast.makeText(this, "Animation rep", Toast.LENGTH_SHORT).show();

	}
}