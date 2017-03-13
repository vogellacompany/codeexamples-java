package com.vogella.java.retrofitgerrit;

import com.google.gson.annotations.SerializedName;

public class ReviewInput {
	
	@SerializedName("Code-Review")
	private String codeReview = "+1";

	public String getCodeReview() {
		return codeReview;
	}

	public void setCodeReview(String codeReview) {
		this.codeReview = codeReview;
	}
}
