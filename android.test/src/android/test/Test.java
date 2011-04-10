package android.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;

public class Test extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://www.vogella.de/article.rss");
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			BufferedReader stream = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = stream.readLine()) != null) {
				builder.append(line);
			}
			System.out.println(builder.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}