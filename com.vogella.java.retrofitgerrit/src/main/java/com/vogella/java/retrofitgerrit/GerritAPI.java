package com.vogella.java.retrofitgerrit;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GerritAPI {

	@GET("changes/?q=status:open&o=CURRENT_REVISION&o=DETAILED_ACCOUNTS")
	Single<List<Change>> getAllChanges();

	@GET
	Single<List<Change>> getChangesForProject(@Url String url);

	@GET
	Single<List<Change>> getChangesForUser(@Url String url);

	@POST("changes/{change-id}/revisions/{revision-id}/review")
	Single<ResponseBody> postUpvote(@Path("change-id") String changeId, @Path("revision-id") String revisionId);
}
