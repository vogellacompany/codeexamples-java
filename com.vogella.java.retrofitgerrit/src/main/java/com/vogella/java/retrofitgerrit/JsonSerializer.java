package com.vogella.java.retrofitgerrit;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class JsonSerializer implements com.google.gson.JsonSerializer<ReviewInput>{

	@Override
	public JsonElement serialize(ReviewInput src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject codeReviewJson = new JsonObject();
		JsonElement codeReviewElement = context.serialize(src.getCodeReview());
		codeReviewJson.add("Code-Review", codeReviewElement);
		
		JsonObject parentJson = new JsonObject();
		JsonElement parentElement = context.serialize(codeReviewJson);
		parentJson.add("labels", parentElement);
		
		return parentJson;
	}

}
