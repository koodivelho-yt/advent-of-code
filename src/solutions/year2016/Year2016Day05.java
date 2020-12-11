package solutions.year2016;

import api.InputParser;
import solver.DayX;
import utils.MD5;

public class Year2016Day05 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		String s = input.string();
		StringBuilder sb = new StringBuilder();

		MD5 m = MD5.get();
		int i = 0;
		while (sb.length() < 8) {
			String c = m.hash(s + i++);
			if (c.startsWith("00000")) {
				sb.append(c.charAt(5));
			}
		}
		return sb;
	}

	@Override
	public Object secondPart(InputParser input) {
		String s = input.string();
		StringBuilder sb = new StringBuilder("_".repeat(8));

		MD5 m = MD5.get();
		long i = 0;
		while (sb.chars().anyMatch(ch -> ch == '_')) {
			String c = m.hash(s + i++);
			if (c.startsWith("00000")) {
				int position = Integer.parseInt("" + c.charAt(5), 16);
				if (position < 8 && sb.charAt(position)=='_') {
					sb.setCharAt(position, c.charAt(6));
					
				}
			}
		}
		return sb;
	}
}