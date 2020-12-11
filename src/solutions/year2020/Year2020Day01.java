package solutions.year2020;

import api.InputParser;
import solver.DayX;

public class Year2020Day01 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		int[] values = input.asSingleIntArray();
		int a = 0, b = 0;
		outer: for (int i : values) {
			for (int j : values) {
				if (i + j == 2020) {
					a = i;
					b = j;
					break outer;
				}
			}
		}
		return a * b;
	}

	@Override
	public Object secondPart(InputParser input) {
		int[] values = input.asSingleIntArray();
		int a = 0, b = 0, c = 0;
		outer: for (int i : values) {
			for (int j : values) {
				for (int k : values) {
					if (i + j + k == 2020) {
						a = i;
						b = j;
						c = k;
						break outer;
					}
				}
			}
		}
		return a * b * c;
	}
}