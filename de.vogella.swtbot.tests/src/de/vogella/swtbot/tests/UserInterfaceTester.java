package de.vogella.swtbot.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class UserInterfaceTester {
	private final SWTWorkbenchBot bot = new SWTWorkbenchBot();

	@Test
	public void test() {
		// Find the text after the label "My Label"
		SWTBotText textWithLabel = bot.textWithLabel("My Label");
		// bot.captureScreenshot("test.png");
		// Set the focus and write a text into the text field
		textWithLabel.setFocus();
		assertEquals(textWithLabel.getText(), "This is my text");
		textWithLabel.selectAll();
		textWithLabel.typeText("Java rules them all");
		assertEquals(textWithLabel.getText(), "Java rules them all - Wrong");

		// Find the text with with the assigned id
		SWTBotText textWithId = bot.textWithId("text1");
		assertEquals(textWithId.getText(), "This text has an ID");

		// Now lets find a view part
		SWTBotView viewById = bot.viewById("de.vogella.swtbot.app.view");
		assertNotNull(viewById);

		// Select the exit menu
		// bot.menu("/File").menu("Exit").click();

		assertTrue(true);
	}
}
