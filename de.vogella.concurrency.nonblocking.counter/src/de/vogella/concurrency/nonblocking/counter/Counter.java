package de.vogella.concurrency.nonblocking.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	private AtomicInteger value = new AtomicInteger();

	public int getValue() {
		return value.get();
	}

	public int increment() {
		return value.incrementAndGet();
	}

	// Alternative implementation as increment but just make the
	// implementation explicit
	public int incrementLongVersion() {
		int oldValue = value.get();
		while (!value.compareAndSet(value.get(), oldValue + 1)) {
			oldValue = value.get();
		}
		return oldValue + 1;
	}

}
