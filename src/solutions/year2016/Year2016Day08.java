package solutions.year2016;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.InputParser;
import solver.DayX;

public class Year2016Day08 extends DayX {

	String rect = "rect (.*)x(.*)";
	String rotate = "rotate (.*) [xy]=(.*) by (.*)";

	@Override
	public Object firstPart(InputParser input) {

		List<String> in = input.getLines();
		char[][] c = new char[6][50];
		for (int x = 0; x < c.length; x++) {
			for (int y = 0; y < c[x].length; y++) {
				c[x][y] = '.';
			}
		}
	
		for (String s : in) {
			if (s.matches(rect)) {
				Matcher m = Pattern.compile(rect).matcher(s);
				m.matches();
				int width = Integer.parseInt(m.group(1));
				int height = Integer.parseInt(m.group(2));

				for (int w = 0; w < width; w++) {
					for (int h = 0; h < height; h++) {
						c[h][w] = '#';
					}
				}
			} else if (s.matches(rotate)) {
				Matcher m = Pattern.compile(rotate).matcher(s);
				m.matches();
				String direction = m.group(1);
				int index = Integer.parseInt(m.group(2));
				int count = Integer.parseInt(m.group(3));

		
				char[] lights;
				if (direction.equals("column")) {
					lights = new char[c.length];
				} else {
					lights = new char[c[0].length];
				}

				for (int i = 0; i < lights.length; i++) {
					if (direction.equals("column")) {
						lights[i] = c[i][index];
					} else {
						lights[i] = c[index][i];
					}
				}
				for (int i = 0; i < lights.length; i++) {
					if (direction.equals("column")) {
						c[(i + count) % lights.length][index] = lights[i];
					} else {
						c[index][(i + count) % lights.length] = lights[i];
					}
				}
			}

		}
		for (char[] row : c) {
			System.out.println(Arrays.toString(row).replaceAll(", ", ""));
		}

		int total = 0;
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				if (c[i][j] == '#') {
					total++;
				}
			}
		}

		return total;
	}

	@Override
	public Object secondPart(InputParser input) {
		System.out.println("See first parts output");
		return NOT_SOLVED;
	}
}