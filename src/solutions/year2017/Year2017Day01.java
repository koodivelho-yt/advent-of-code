package solutions.year2017;

import api.InputParser;
import solver.DayX;
import utils.Delimiter;

public class Year2017Day01 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		return sum(input.joinLinesToString(Delimiter.NONE), 1);
	}

	@Override
	public Object secondPart(InputParser input) {
		String s = input.joinLinesToString(Delimiter.NONE);
		return sum(s, s.length() / 2);
	}

	private static int sum(String s, int offset) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt((i + offset) % s.length())) {
				sum += Integer.parseInt(s.charAt(i) + "");
			}
		}
		return sum;
	}
}
