package de.vogella.gwt.helloserver.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.vogella.gwt.helloserver.client.model.MyUser;
import de.vogella.gwt.helloserver.client.service.MyUserService;

public class MyUserServiceImpl extends RemoteServiceServlet implements
		MyUserService {

	private static final long serialVersionUID = 1L;

	private List<MyUser> userList = new ArrayList<MyUser>();

	public MyUserServiceImpl() {
		MyUser user = new MyUser();
		user.setId("1");
		user.setUsername("Peter");
		user.setNumberOfHits("15");
		userList.add(user);

		user = new MyUser();
		user.setId("2");
		user.setUsername("Hanz");
		user.setNumberOfHits("25");
		userList.add(user);
	}

	public MyUser getUser(String id) {

		for (Object object : userList) {
			if (((MyUser) object).getId().equals(id))
				return ((MyUser) object);
		}
		return null;
	}

	public List<MyUser> getUserList() {
		return userList;
	}
}
