package com.vogella.java.retrofitgerrit;

import com.google.gson.annotations.SerializedName;

public class Account {

	@SerializedName("_account_id")
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
