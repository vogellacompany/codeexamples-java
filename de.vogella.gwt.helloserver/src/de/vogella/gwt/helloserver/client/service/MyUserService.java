package de.vogella.gwt.helloserver.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.vogella.gwt.helloserver.client.model.MyUser;

// 
@RemoteServiceRelativePath("userService")
public interface MyUserService extends RemoteService {
	List<MyUser> getUserList();
	MyUser getUser(String id);
}
