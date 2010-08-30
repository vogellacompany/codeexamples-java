package de.vogella.gwt.helloserver.client.service;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.vogella.gwt.helloserver.client.model.MyUser;
import de.vogella.gwt.helloserver.client.table.DataSource;
import de.vogella.gwt.helloserver.client.table.MyTable;

/**
 * Class which handles the asynchronous callback from the server
 * <p>
 * Need to react on server communication failure and success
 * 
 * @author Lars Vogel
 * 
 */
public class MyUserCallback implements AsyncCallback<List<MyUser>> {

	private MyTable table;

	public MyUserCallback(MyTable table) {
		this.table = table;
	}

	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	public void onSuccess(List<MyUser> result) {
		List<MyUser> users = result;
		DataSource datasource = new DataSource(users);
		table.setInput(datasource);
		for (MyUser user : users) {
			System.out.println(user.getUsername());
		}
	}

}
