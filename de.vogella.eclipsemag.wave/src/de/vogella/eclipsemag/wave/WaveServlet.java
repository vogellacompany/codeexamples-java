package de.vogella.eclipsemag.wave;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.wave.api.AbstractRobot;
import com.google.wave.api.Blip;
import com.google.wave.api.event.BlipSubmittedEvent;
import com.google.wave.api.event.WaveletParticipantsChangedEvent;
import com.google.wave.api.event.WaveletSelfAddedEvent;

public class WaveServlet extends AbstractRobot {
	private static final String twitterUserName = "replace";
	private static final String twitterPassword = "replace";

	@Override
	protected String getRobotName() {
		return "Retweety";
	}

	@Override
	public void onWaveletSelfAdded(WaveletSelfAddedEvent event) {
		event.getWavelet().reply("\nHello Wave. I have been added");
	}

	@Override
	public void onWaveletParticipantsChanged(
			WaveletParticipantsChangedEvent event) {
		for (String participant : event.getParticipantsAdded()) {
			event.getWavelet().reply("\nHello " + participant);
		}

		for (String participant : event.getParticipantsRemoved()) {
			event.getWavelet().reply("\nGoodbye " + participant);
		}
	}

	@Override
	public void onBlipSubmitted(BlipSubmittedEvent event) {
		
		Blip blip = event.getBlip();
		String text = blip.getContent();
		if (text.length() > 140) {
			text = text.substring(0, 139);
		}
		try {
			Twitter twitter = new Twitter(twitterUserName, twitterPassword);
			twitter.updateStatus(text);
			event.getWavelet().reply("\nUpdated twitter");

		} catch (TwitterException e1) {
			event.getWavelet().reply("\nFailure in updating twitter:  " + e1.getMessage());
		}

	}

}
