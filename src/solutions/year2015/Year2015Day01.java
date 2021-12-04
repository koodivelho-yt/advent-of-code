package solutions.year2015;

import java.util.Arrays;

import api.InputParser;
import solver.DayX;

public class Year2015Day01 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		String s = input.string();

		return Arrays.stream(s.split("")).mapToInt(e -> e.equals("(") ? 1 : -1).sum();

	}

	@Override
	public Object secondPart(InputParser input) {
		String s = input.string();
		int[] val = Arrays.stream(s.split("")).mapToInt(e -> e.equals("(") ? 1 : -1).toArray();

		int level = 0;
		int index = 0;
		do {
			level += val[index];
			index++;
		} while (level >= 0);

		return index;
	}

}
