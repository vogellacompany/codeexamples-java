package de.vogella.xml.stax.writer;

public class TestWrite {

	public static void main(String[] args) {
		StaxWriter configFile = new StaxWriter();
		configFile.setFile("config2.xml");
		try {
			configFile.saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
