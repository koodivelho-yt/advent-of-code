package solutions.year2019.intcodecomputer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class IntcodeComputer {

	private Instruction[] opcodeTable;

	private int[] program;
	private int instructionPointer = 0;
	private LinkedBlockingQueue<Integer> input;
	private LinkedBlockingQueue<Integer> output;
	
	ReentrantLock lock = new ReentrantLock();

	public static IntcodeComputer day2(int[] program) {
		return new IntcodeComputer(program);
	}

	public static IntcodeComputer day5capable(int[] program) {
		IntcodeComputer ic = new IntcodeComputer(program);
		ic.loadDay5extendedInstructions();
		return ic;
	}

	private IntcodeComputer(int[] program) {
		this.program = program;
		loadDefaultInstructions();
		input = new LinkedBlockingQueue<Integer>();
		output = new LinkedBlockingQueue<Integer>();

	}

	private void loadDay5extendedInstructions() {
		// input
		opcodeTable[3] = (opcode) -> {
			try {
				int[] modes = parseModes(opcode);
				lock.unlock();
				int in = input.poll(1, TimeUnit.HOURS);
				lock.lock();
				int index = program[instructionPointer + 1];
				program[index] = in;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return 2;
		};

		// output
		opcodeTable[4] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int op = modes[0] == 0 ? program[program[instructionPointer + 1]] : program[instructionPointer + 1];
			output.add(op);
			return 2;
		};

		// jump-if-true
		opcodeTable[5] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;
			if (program[index1] != 0) {
				instructionPointer = program[index2];
				return 0;
			} else {
				return 3;
			}
		};
		// jump-if-false
		opcodeTable[6] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;

			if (program[index1] == 0) {
				instructionPointer = program[index2];
				return 0;
			} else {
				return 3;
			}
		};

		// less-than
		opcodeTable[7] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;
			int index3 = modes[2] == 0 ? program[instructionPointer + 3] : instructionPointer + 3;

			if (program[index1] < program[index2]) {
				program[index3] = 1;
			} else {
				program[index3] = 0;
			}
			return 4;
		};

		// equal
		opcodeTable[8] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;
			int index3 = modes[2] == 0 ? program[instructionPointer + 3] : instructionPointer + 3;

			if (program[index1] == program[index2]) {
				program[index3] = 1;
			} else {
				program[index3] = 0;
			}
			return 4;
		};

	}

	private int[] parseModes(int opcode) {
		opcode = opcode + 100000;
		String s = "" + opcode;
		s = s.substring(1, 4);
		int m3 = Integer.parseInt("" + s.charAt(0));
		int m2 = Integer.parseInt("" + s.charAt(1));
		int m1 = Integer.parseInt("" + s.charAt(2));
		return new int[] { m1, m2, m3 };
	}

	private void loadDefaultInstructions() {
		opcodeTable = new Instruction[100];

		// add
		opcodeTable[1] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;
			int index3 = modes[2] == 0 ? program[instructionPointer + 3] : instructionPointer + 3;

			program[index3] = program[index1] + program[index2];
			return 4;
		};

		// mul
		opcodeTable[2] = (opcode) -> {
			int[] modes = parseModes(opcode);
			int index1 = modes[0] == 0 ? program[instructionPointer + 1] : instructionPointer + 1;
			int index2 = modes[1] == 0 ? program[instructionPointer + 2] : instructionPointer + 2;
			int index3 = modes[2] == 0 ? program[instructionPointer + 3] : instructionPointer + 3;

			program[index3] = program[index1] * program[index2];

			return 4;
		};

		opcodeTable[99] = (opcode) -> {
			return 0;
		};
	}

	public void executeNext() {
		int opcode = program[instructionPointer] % 100;
		int target = opcodeTable[opcode].execute(program[instructionPointer]);
		instructionPointer += target;
	}

	public int runProgram() {

		lock.lock();
		while (program[instructionPointer] != 99) {
			executeNext();
			
		}
		lock.unlock();

		return program[0];
	}
	


	public interface Instruction {

		public int execute(int modes);

	}

	public Integer readNext() {
		while (true) {
			try {
				return output.poll(5, TimeUnit.HOURS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public IntcodeComputer writeInput(int i) {
		input.add(i);
		return this;
	}

	public boolean hasOutput() {
		return !output.isEmpty();
	}

	public void waitHalt() {
		System.out.println("Waited halt");
		lock.lock();
		lock.unlock();
		System.out.println("Wait ended");
	}

	public boolean hasEnded() {
		System.out.println(input.size()+" " +output.size()+" "+program[instructionPointer]);
		return program[instructionPointer] == 99;
	}
	public void start() {
		new Thread(() -> this.runProgram()).start();
	}
}
