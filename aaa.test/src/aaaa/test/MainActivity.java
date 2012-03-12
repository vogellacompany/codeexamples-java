package aaaa.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		BufferedReader reader = null;
		URL url = null;
		try {
			url = new URL("http://www.vogella.de");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			InputStream in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			String s = "";
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
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

	} // public void onClick(View view) {
		// try {
		// URL url = new URL("http://www.vogella.de");
		// HttpURLConnection connection = (HttpURLConnection) url
		// .openConnection();
		// readStream(connection.getInputStream());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		//
		// private void readStream(InputStream in) throws IOException {
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(in));
		// String line = "";
		// while ((line = reader.readLine()) != null) {
		// System.out.println(line);
		// }
		// }
}