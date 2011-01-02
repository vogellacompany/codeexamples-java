package de.vogella.android.soundpool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class PlaySound extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void playSound(View view) {
		// First parameter defines the number of channels which should be played
		// in parallel, last one currently not used
		SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		int soundID = soundPool.load(this, R.raw.sound1, 1);

		// Getting the user sound settings
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		soundPool.play(soundID, volume, volume, 1, 0, 1f);
	}
}