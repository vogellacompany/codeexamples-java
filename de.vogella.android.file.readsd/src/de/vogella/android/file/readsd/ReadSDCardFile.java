package de.vogella.android.file.readsd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class ReadSDCardFile extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		writeFileToInternalStorage();
		readFileFromSDCard();
	}

	private void writeFileToInternalStorage() {
		String eol = System.getProperty("line.separator");
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					openFileOutput("myfile", MODE_WORLD_WRITEABLE)));
			writer.write("This is a test1." + eol);
			writer.write("This is a test2." + eol);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					openFileInput("myfile")));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = input.readLine()) != null) {
				buffer.append(line + eol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFileFromSDCard() {
		File directory = Environment.getExternalStorageDirectory();
		// Assumes that a file article.rss is availalbe on the SD card
		File file = new File(directory + "/article.rss");
		if (!file.exists()) {
			throw new RuntimeException("File not found");
		}
		Log.e("Testing", "Starting to read");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}