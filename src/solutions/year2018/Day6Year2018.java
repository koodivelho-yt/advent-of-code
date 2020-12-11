package solutions.year2018;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import api.InputParser;
import solver.DayX;
import utils.Delimiter;

public class Day6Year2018 extends DayX{

	@Override
	public Object firstPart(InputParser input){
		int[] allNumbers = input.asSingleIntArray(Delimiter.COMMA);
		int max = Arrays.stream(allNumbers).max().getAsInt();
		int[][] table = new int[max][max];
		ArrayList<Point> points = new ArrayList<>();
		for(int i = 0; i < allNumbers.length; i += 2){
			int x = allNumbers[i];
			int y = allNumbers[i + 1];
			table[x][y] = i;
			Point p = new Point(x, y);
			points.add(p);
		}
		for(int i = 0; i < max; i++){
			for(int j = 0; j < max; j++){

			}
		}

		return null;
	}

	@Override
	public Object secondPart(InputParser input){
		return NOT_SOLVED;
	}

}
