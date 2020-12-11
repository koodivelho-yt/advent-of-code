package solutions.year2020;

import java.util.List;

import api.InputParser;
import solver.DayX;

public class Year2020Day03 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		List<String> lines = input.getLines();

		int right = 3;
		int current = 0;
		int trees = 0;
		for (int i = 1; i < lines.size(); i++) {
			current += right;
			String line = lines.get(i);
			char tree = line.charAt(current % line.length());
			if (tree == '#') {
				trees++;
			}
		}
		System.out.println(trees);
		return trees;

	}

	@Override
	public Object secondPart(InputParser input) {
		/*
		 * 
		 * Right 1, down 1. Right 3, down 1. (This is the slope you already checked.)
		 * Right 5, down 1. Right 7, down 1. Right 1, down 2.
		 * 
		 */
		List<String> lines = input.getLines();
		int[] rights = { 1, 3, 5, 7, 1 };
		int[] downs = { 1, 1, 1, 1, 2 };
		long result = 1;
		for (int r = 0; r < rights.length; r++) {
			int right = rights[r];
			int current = 0;
			int trees = 0;
			for (int i = 0; i < lines.size();) {
				current += right;
				i += downs[r];
				if (i >= lines.size())
					break;
				String line = lines.get(i);
				char tree = line.charAt(current % line.length());
				if (tree == '#') {
					trees++;
				}
			}
			result *= trees;
		}
		System.out.println(result);
		return result;
	}
}