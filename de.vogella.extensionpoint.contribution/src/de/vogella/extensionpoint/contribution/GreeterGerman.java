package de.vogella.extensionpoint.contribution;

import de.vogella.extensionpoint.definition.IGreeter;

public class GreeterGerman implements IGreeter {

	@Override
	public void greet() {
		System.out.println("Moin Jungs!");
	}
}
