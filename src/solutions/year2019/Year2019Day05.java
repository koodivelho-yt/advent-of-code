package solutions.year2019;

import java.util.Arrays;

import api.InputParser;
import solutions.year2019.intcodecomputer.IntcodeComputer;
import solver.DayX;
import utils.Delimiter;

public class Year2019Day05 extends DayX {

	@Override
	public Object firstPart(InputParser input) {
		int[] program = input.asSingleIntArray(Delimiter.COMMA);
		IntcodeComputer ic = IntcodeComputer.day5capable(program);
		ic.writeInput(1);
		ic.runProgram();
		
		int result=0;
		int checksum=0;
		while(ic.hasOutput()) {
			int o = ic.readNext();
			if(o == 0) {
				checksum+=o;
			}else {
				result = o;
				break;
			}
		}
		if(ic.hasOutput()) {
			System.out.println("more values to read");
			System.out.println("checksum failed as value" +checksum);
			return NOT_SOLVED;

		}
		
		return result;
	}

	@Override
	public Object secondPart(InputParser input) {
		int[] program = input.asSingleIntArray(Delimiter.COMMA);
		IntcodeComputer ic = IntcodeComputer.day5capable(program);
		ic.writeInput(5);
		ic.runProgram();
		
		
		return ic.readNext();
	}
}