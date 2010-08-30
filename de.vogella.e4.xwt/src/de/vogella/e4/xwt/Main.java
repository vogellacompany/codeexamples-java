package de.vogella.e4.xwt;
import java.net.URL;

import org.eclipse.e4.xwt.XWT;

public class Main {
	public static void main(String[] args) {
		URL content = XwtComponent1.class.getResource("XwtComponent1.xwt");
		try {
			XWT.open(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
