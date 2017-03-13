package com.vogella.java.retrofitgerrit;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

	private static final String BASE_URL = "https://git.eclipse.org/r/";
	private GerritAPI gerritAPI;
	private CompositeDisposable compositeDisposable = new CompositeDisposable();
	private UserInterface userInterface;
	protected List<Change> currentData;

	public Controller(UserInterface userInterface) {
		this.userInterface = userInterface;
		initGerritApi();
	}

	private void initGerritApi() {
		HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();
		 loggerInterceptor.setLevel(Level.BODY);

		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggerInterceptor).build();

		Gson gson = new GsonBuilder().registerTypeAdapter(ReviewInput.class, new JsonSerializer()).setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();

		gerritAPI = retrofit.create(GerritAPI.class);
	}

	public void loadAllChanges() {
		compositeDisposable.add(gerritAPI.getAllChanges().subscribeOn(Schedulers.io()).observeOn(Schedulers.single())
				.subscribeWith(getChangesObserver()));
	}

	public void loadChangesForProject(String project) {
		String url = BASE_URL + "changes/?o=CURRENT_REVISION&o=DETAILED_ACCOUNTS&q=status:open+project:"
				+ project.replace("/", "%2F");
		compositeDisposable.add(gerritAPI.getChangesForProject(url).subscribeOn(Schedulers.io())
				.observeOn(Schedulers.single()).subscribeWith(getChangesObserver()));
	}

	public void loadChangesForUser(int userId) {
		String url = BASE_URL + "changes/?o=CURRENT_REVISION&o=DETAILED_ACCOUNTS&q=status:open+owner:" + userId;
		compositeDisposable.add(gerritAPI.getChangesForUser(url).subscribeOn(Schedulers.io())
				.observeOn(Schedulers.single()).subscribeWith(getChangesObserver()));
	}

	public void upvote(String username, String password, int i) {
		Change currentChange = currentData.get(i);
		compositeDisposable.add(gerritAPI
				.postUpvote(Credentials.basic(username, username), currentChange.getChangeId(),
						currentChange.getCurrentRevision(), new ReviewInput())
				.subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribeWith(getUpvoteObserver()));
	}

	private DisposableSingleObserver<List<Change>> getChangesObserver() {
		return new DisposableSingleObserver<List<Change>>() {

			@Override
			public void onSuccess(List<Change> value) {
				Controller.this.currentData = value;
				userInterface.updateList(value);
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}
		};
	}

	private DisposableSingleObserver<ResponseBody> getUpvoteObserver() {
		return new DisposableSingleObserver<ResponseBody>() {

			@Override
			public void onSuccess(ResponseBody value) {

			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}
		};
	}

	public void getChangeInformation(int selectionIndex) {
		userInterface.updateTextFields(currentData.get(selectionIndex));
	}
}
