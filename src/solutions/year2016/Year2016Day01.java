package solutions.year2016;

import java.util.HashSet;
import java.util.Set;

import api.InputParser;
import solver.DayX;
import utils.Delimiter;

public class Year2016Day01 extends DayX {
	private Set<String> visited = new HashSet<String>();
	private int result = -1;

	@Override
	public Object firstPart(InputParser input) {
		String[] in = input.rowsAsOneArray(Delimiter.COMMA);
		int x = 0;
		int y = 0;
		Direction d = Direction.NORTH;
		for (String s : in) {

			if (s.charAt(0) == 'L') {
				d = d.previous();
			} else {
				d = d.next();
			}
			int a = Integer.parseInt(s.substring(1));
			for (int i = 0; i < a; i++) {
				x += d.dx;
				y += d.dy;
				if (result==-1 && !visited.add(x + "," + y)) {
					result= Math.abs(x) + Math.abs(y);
				}
			}
		}
		return Math.abs(x) + Math.abs(y);
	}

	@Override
	public Object secondPart(InputParser input) {
		if(result==-1) {
			firstPart(input);
		}
		return result;
	}

	private enum Direction {
		NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

		int dx;
		int dy;

		private Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		public Direction next() {
			return Direction.values()[((this.ordinal() + 1) % 4)];
		}

		public Direction previous() {
			return Direction.values()[(((this.ordinal() + 3) % 4))];
		}

	}

}