package com.vogella.java.retrofitxml;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller implements Callback<VogellaRSSFeed> {

	static final String BASE_URL = "http://vogella.com/";

	public void start() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(SimpleXmlConverterFactory.create()).build();

		VogellaAPI vogellaAPI = retrofit.create(VogellaAPI.class);

		Call<VogellaRSSFeed> call = vogellaAPI.loadRSSFeed();
		call.enqueue(this);
	}

	@Override
	public void onResponse(Call<VogellaRSSFeed> call, Response<VogellaRSSFeed> response) {
		if (response.isSuccessful()) {
			VogellaRSSFeed rss = response.body();
			System.out.println("Channel title: " + rss.getChannelTitle());
			rss.getArticleList().forEach(
					article -> System.out.println("Title: " + article.getTitle() + " Link: " + article.getLink()));
		} else {
			System.out.println(response.errorBody());
		}
	}

	@Override
	public void onFailure(Call<VogellaRSSFeed> call, Throwable t) {
		t.printStackTrace();
	}
}
