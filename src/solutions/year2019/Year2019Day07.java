package solutions.year2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import api.InputParser;
import solutions.year2019.intcodecomputer.IntcodeComputer;
import solver.DayX;

public class Year2019Day07 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		int[] program = input.intCodeProgram();

		List<Integer> sequence = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
		IntcodeComputer[] computers;
		int max = 0;
		do {
			computers = new IntcodeComputer[5];
			for (int i = 0; i < computers.length; i++) {
				computers[i] = IntcodeComputer.day5capable(program);
			}
			computers[0].writeInput(sequence.get(0)).writeInput(0);
			computers[0].runProgram();

			for (int i = 0; i < computers.length - 1; i++) {
				int out = computers[i].readNext();
				computers[i + 1].writeInput(sequence.get(i + 1)).writeInput(out);
				computers[i + 1].runProgram();
			}

			int mx = computers[4].readNext();
			if (mx > max) {
				max = mx;
			}
		} while (hasNextPermutation(sequence));

		return max;
	}

	@Override
	public Object secondPart(InputParser input) {
		int[] program = { 3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005,
				28, 6, 99, 0, 0, 5 };// input.intCodeProgram();

		List<Integer> sequence = new ArrayList<Integer>(Arrays.asList(9, 8, 7, 6, 5));
		IntcodeComputer[] computers = new IntcodeComputer[5];

		int max = 0;

		for (int i = 0; i < computers.length; i++) {
			computers[i] = IntcodeComputer.day5capable(program);
			computers[i].start();
			computers[i].writeInput(sequence.indexOf(i));
		}

		computers[0].writeInput(0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0;; i++) {
			computers[i].waitHalt();
			if (!computers[i].hasEnded())
				computers[(i + 1) % 5].writeInput(computers[i % 5].readNext());
				
			else
				break;
		}

		int mx = computers[4].readNext();

		if (mx > max) {
			max = mx;
		}

		System.out.println(mx);
		return NOT_SOLVED;
	}

	public boolean hasNextPermutation(List<Integer> list) {
		int k = -1;
		for (int i = list.size() - 1; i > 0; i--) {
			if (list.get(i - 1).compareTo(list.get(i)) < 0) {
				k = i - 1;
				break;
			}
		}
		if (k == -1) {
			return false;
		}
		int l = list.size();
		for (int i = list.size() - 1; i > k; i--) {
			if (list.get(k).compareTo(list.get(i)) < 0) {
				l = i;
				break;
			}
		}
		swap(k, l, list);
		reverse(k + 1, list);
		return true;
	}

	private void swap(int k, int i, List<Integer> list) {
		Integer tmp = list.get(i);
		list.set(i, list.get(k));
		list.set(k, tmp);
	}

	private void reverse(int start, List<Integer> list) {
		List<Integer> endl = new ArrayList<Integer>(list.subList(start, list.size()));
		list.removeAll(endl);
		Collections.reverse(endl);
		list.addAll(endl);
	}

}