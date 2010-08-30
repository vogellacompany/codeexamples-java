package de.vogella.webservice.soap.axis2;

import java.util.Random;

public class RandomNumber {
	public long getNumber(){
		Random random = new Random(1000);
		return random.nextLong();
	}
}
