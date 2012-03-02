package de.vogella.android.test.target.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import de.vogella.android.test.target.R;
import de.vogella.android.test.target.SimpleActivity;

public class SimpleActivityTestStandard extends
		ActivityUnitTestCase<SimpleActivity> {

	public SimpleActivityTestStandard() {
		super(SimpleActivity.class);
	}

	public void setUp() throws Exception {
		startActivity(new Intent(getInstrumentation().getTargetContext(),
				SimpleActivity.class), null, null);

	}

	public void testLayout() {
		SimpleActivity activity = getActivity();
		assertNotNull(activity.findViewById(R.id.button1));
		Button view = (Button) activity
				.findViewById(de.vogella.android.test.target.R.id.button1);
		assertEquals("My Button 1", view.getText());
	}

}