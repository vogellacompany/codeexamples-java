package de.vogella.xml.stax.read;

import java.util.List;

import de.vogella.xml.stax.model.Item;

public class TestRead {
	public static void main(String args[]) {
		StaXParser read = new StaXParser();
		List<Item> readConfig = read.readConfig("config.xml");
		for (Item item : readConfig) {
			System.out.println(item);
		}
	}
}
