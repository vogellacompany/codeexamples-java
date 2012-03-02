package de.vogella.android.test.target.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.widget.Button;
import de.vogella.android.test.target.R;
import de.vogella.android.test.target.SimpleActivity;

public class SimpleActivityTestCross extends
		ActivityInstrumentationTestCase2<SimpleActivity> {

	public SimpleActivityTestCross() {
		super(SimpleActivity.class);
	}

	public void testPreferenceIsSaved() throws Exception {
		Instrumentation instr = getInstrumentation();
		SimpleActivity activity = getActivity();
		Button view = (Button) activity.findViewById(R.id.button1);
		TouchUtils.clickView(this, view);
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);

	}
}
