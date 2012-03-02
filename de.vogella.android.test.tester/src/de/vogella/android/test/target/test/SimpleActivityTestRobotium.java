package de.vogella.android.test.target.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import de.vogella.android.test.target.SimpleActivity;
import de.vogella.android.test.target.SimpleListActivity;

public class SimpleActivityTestRobotium extends
		ActivityInstrumentationTestCase2<SimpleActivity> {

	private Solo solo;

	public SimpleActivityTestRobotium() {
		super(SimpleActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testPreferenceIsSaved() throws Exception {
		// Check that we have the right activity
		solo.assertCurrentActivity("wrong activiy", SimpleActivity.class);

		// Click a button which will start a new Activity
		// Here we use the ID of the string to find the right button
		solo.clickOnButton(solo
				.getString(de.vogella.android.test.target.R.string.button1));
		// Validate that the Activity is the correct one
		solo.assertCurrentActivity("wrong activiy", SimpleListActivity.class);
		solo.clickInList(1);
		// searchForText has a timeout of 5 seconds
		assertTrue(solo.waitForText("Android")); // Assertion
		solo.clickInList(2);
		assertTrue(solo.waitForText("iPhone")); // Assertion
		solo.clickInList(3);
		assertTrue(solo.waitForText("Blackberry")); // Assertion
		solo.goBack();
		solo.clickOnButton("Button2");
		solo.clickOnButton("Button3");

		// Open the menu
		solo.sendKey(Solo.MENU);
		solo.clickOnText("Preferences");
		solo.clickOnText("User");
		solo.clearEditText(0);
		Assert.assertTrue(solo.searchText(""));
		solo.enterText(0, "http//:www.vogella.de");
		Assert.assertTrue(solo.searchText("http//:www.vogella.de"));
		solo.goBack();

	}

	@Override
	public void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {

			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}
}