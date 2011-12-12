package de.vogella.android.codecamp;

import org.apache.http.client.HttpClient;

import android.app.Application;
import android.net.http.AndroidHttpClient;

public class MyApplication extends Application {
	HttpClient client = AndroidHttpClient.newInstance("");
}
