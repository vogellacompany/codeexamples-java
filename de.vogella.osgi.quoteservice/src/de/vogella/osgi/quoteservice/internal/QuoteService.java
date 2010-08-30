package de.vogella.osgi.quoteservice.internal;

import java.util.Random;

import de.vogella.osgi.quote.IQuoteService;

public class QuoteService implements IQuoteService {

	@Override
	public String getQuote() {
		Random random = new Random();
		// Create a number between 0 and 2
		int nextInt = random.nextInt(3);
		switch (nextInt) {
		case 0:
			return "Tell them I said something";
		case 1:
			return "I feel better already";
		default:
			return "Hubba Bubba, Baby!";
		}

	}
}
