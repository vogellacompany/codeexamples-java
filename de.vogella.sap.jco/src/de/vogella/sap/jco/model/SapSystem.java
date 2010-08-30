package de.vogella.sap.jco.model;

public class SapSystem {
	private String client;
	private String user;
	private String password;
	private String language;
	private String host;
	private String sysID;

	public void setClient(String client) {
		this.client = client;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setSystemNumber(String sysID) {
		this.sysID = sysID;
	}

	public String getClient() {
		return client;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getLanguage() {
		return language;
	}

	public String getHost() {
		return host;
	}

	public String getSystemNumber() {
		return sysID;
	}

	public String toString() {
		String s = "";
		s += "Client " + client;
		s += " User " + user;
		s += " Password " + password;
		s += " Host " + host;
		s += " Language " + language;
		return s;
	}
}
