package solutions.year2019;

import api.InputParser;
import solutions.year2019.intcodecomputer.IntcodeComputer;
import solver.DayX;
import utils.Delimiter;

public class Year2019Day02 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		long[] values = input.intCodeProgram();
		values[1] = 12;
		values[2] = 2;
		IntcodeComputer ic = IntcodeComputer.day2(values);
		return ic.runProgram();
	}

	@Override
	public Object secondPart(InputParser input) {

		int c = 19690720;
		long[] values = input.intCodeProgram();

		int i = 0;
		int noun = 0;
		int verb = 0;
		long[] program;
		IntcodeComputer ic;
		do {
			program = new long[values.length];
			System.arraycopy(values, 0, program, 0, values.length);
			noun = i / 100;
			verb = i % 100;
			program[1] = noun;
			program[2] = verb;
			i++;
			ic = IntcodeComputer.day2(program);
		} while (ic.runProgram() != c);

		return 100 * noun + verb;
	}

}
