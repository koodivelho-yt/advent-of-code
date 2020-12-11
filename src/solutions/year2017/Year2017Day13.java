package solutions.year2017;

import java.util.List;

import api.InputParser;
import solver.DayX;

public class Year2017Day13 extends DayX{
	private int[][] firewallData;

	private static int testFirewall(int[][] data, int delay){
		int severity = 0;
		for(int i = 0; i < data.length; i++){
			if((data[i][0] + delay) % data[i][2] == 0){
				// if we get caught on index 0, severity would be 0 if we don't
				// increase.
				if(data[i][0] == 0)
					severity++;
				severity += data[i][0] * data[i][1];
			}
		}
		return severity;
	}

	@Override
	public Object firstPart(InputParser input){
		List<String> lines = input.getLines();

		int[][] data = new int[lines.size()][3];
		int k = 0;
		for(String s: lines){
			String[] values = s.split(": ");
			data[k][0] = Integer.parseInt(values[0]);
			data[k][1] = Integer.parseInt(values[1]);
			data[k][2] = (Integer.parseInt(values[1]) - 1) * 2;
			k++;
		}
		this.firewallData = data;
		return testFirewall(firewallData, 0);
	}

	@Override
	public Object secondPart(InputParser input){
		if(firewallData == null){
			firstPart(input);
		}
		int result = 0;
		int delay = -1;
		do{
			delay++;
			result = testFirewall(firewallData, delay);
		} while(result != 0);
		return delay;
	}
}
