package de.vogella.gwt.helloserver.client.table;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;

import de.vogella.gwt.helloserver.client.model.MyUser;

public class MyTable extends FlexTable {
	DataSource input;

	public MyTable(DataSource input) {
		super();
		this.setCellPadding(1);
		this.setCellSpacing(0);
		this.setWidth("100%");
		this.setInput(input);
	}

	public void setInput(DataSource input) {
		for (int i = this.getRowCount(); i > 0; i--) {
			this.removeRow(0);
		}
		if (input == null) {
			return;
		}

		int row = 0;
		List<String> headers = input.getTableHeader();
		if (headers != null) {
			int i = 0;
			for (String string : headers) {
				this.setText(row, i, string);
				i++;
			}
			row++;
		}
		// Make the table header look nicer
		this.getRowFormatter().addStyleName(0, "tableHeader");

		List<MyUser> rows = input.getUsers();
		int i = 1;
		for (MyUser myUser : rows) {
			this.setText(i, 0, myUser.getId());
			this.setText(i, 1, myUser.getUsername());
			this.setText(i, 2, myUser.getNumberOfHits());
			i++;
		}
		this.input = input;
	}
}
