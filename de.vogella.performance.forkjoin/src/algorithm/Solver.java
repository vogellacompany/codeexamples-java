package algorithm;

import java.util.Arrays;

import jsr166y.forkjoin.RecursiveAction;

public class Solver extends RecursiveAction {
	private int[] list;
	private long result;

	public Solver(int[] array) {
		this.list = array;
	}

	public long getResult() {
		return result;
	}

	@Override
	protected void compute() {
		if (list.length == 1) {
			result = list[0];
		} else {
			int midpoint = list.length / 2;
			int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
			int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
			Solver s1 = new Solver(l1);
			Solver s2 = new Solver(l2);
			forkJoin(s1, s2);
			result = s1.getResult() + s2.getResult();
		}
	}
}
