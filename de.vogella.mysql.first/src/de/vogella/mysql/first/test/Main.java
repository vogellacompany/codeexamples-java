package de.vogella.mysql.first.test;

import de.vogella.mysql.first.MySQLAccess;

public class Main {
	public static void main(String[] args) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
	}

}
