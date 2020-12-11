package solutions.year2015;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.InputParser;
import solver.DayX;

public class Year2015Day12 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		//instead of using a library, we can just find all numbers
		Matcher m = Pattern.compile("-?[0-9]+").matcher(input.string());
		int sum = 0;
		while (m.find()) {
			sum += Integer.parseInt(m.group());
		}
		return sum;
	}

	@Override
	public Object secondPart(InputParser input) {

		return NOT_SOLVED;
	}
}