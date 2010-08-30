package de.vogella.gwt.helloserver.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.vogella.gwt.helloserver.client.model.MyUser;

public interface MyUserServiceAsync {
	void getUserList(AsyncCallback<List<MyUser>> callback);
	void getUser(String id, AsyncCallback<MyUser> callback);
}