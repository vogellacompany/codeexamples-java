package de.vogella.concurrency.nonblocking.counter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
		private static final int NTHREDS = 10;

		public static void main(String[] args) {
			final Counter counter = new Counter();
			List<Future<Integer>> list = new ArrayList<Future<Integer>>();

			ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
			for (int i = 0; i < 500; i++) {
				Callable<Integer> worker = new  Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						int number = counter.increment();
						System.out.println(number );
						return number ;
					}
				};
				Future<Integer> submit= executor.submit(worker);
				list.add(submit);

			}
			
			
			// This will make the executor accept no new threads
			// and finish all existing threads in the queue
			executor.shutdown();
			// Wait until all threads are finish
			while (!executor.isTerminated()) {
			}
			Set<Integer> set = new HashSet<Integer>();
			for (Future<Integer> future : list) {
				try {
					set.add(future.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			if (list.size()!=set.size()){
				throw new RuntimeException("Double-entries!!!"); 
			}

		}


}
