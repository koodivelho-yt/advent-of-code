package solutions.year2020;

import java.util.List;

import api.InputParser;
import solutions.year2020.computer.Computer;
import solver.DayX;
import utils.Delimiter;

public class Year2020Day08 extends DayX {




	@Override
	public Object firstPart(InputParser input) {

		List<List<String>> lines = input.linesAsLists(Delimiter.SPACE);
		Computer c = new Computer(Computer.getProgram(lines));

		c.detectInfiniteLoop();
		long acc = c.getAcc();
		return acc;
	}

	@Override
	public Object secondPart(InputParser input) {
		List<List<String>> lines = input.linesAsLists(Delimiter.SPACE);
		
		int index = 0;
		Computer c;

		do {
			int[] prog = Computer.getProgram(lines);
			for (; index < prog.length; index += 2) {

				if (prog[index] == Computer.NOP || prog[index] == Computer.JMP) {
					prog[index] = Math.abs(prog[index] - 2);
					index += 2;
					break;
				}
			}
			c = new Computer(prog);
		} while (c.detectInfiniteLoop());

		return c.getAcc();
	}

}