package solutions.year2018;

import api.InputParser;
import solver.DayX;

public class Year2018Day06 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		/**
		 * Calculate triangles of every three values. If a value is inside a triangle,
		 * it's not infinite? If a value is outside a triangle, it's infinite. 19500
		 * triangles? test each value against all triangles to determine the infinite
		 * values. Then fill the area between min-value and max-value matrix with the
		 * distances. Then calculate areas?
		 * 
		 * This might be unnecessary. If you just fill the area, all that touch the
		 * border will be infinite. Based on reddit, no. Some proofs needed still, but
		 * another alternative is to increase the area
		 * 
		 * 
		 */
		return NOT_SOLVED;
	}

	@Override
	public Object secondPart(InputParser input) {
		return NOT_SOLVED;
	}
}