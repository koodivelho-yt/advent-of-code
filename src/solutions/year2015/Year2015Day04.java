package solutions.year2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import api.InputParser;
import solver.DayX;
import utils.Delimiter;

public class Year2015Day04 extends DayX {
	int result2 = 0;

	@Override
	public Object firstPart(InputParser input) {
		String seed = input.joinLinesToString(Delimiter.NONE);

		MessageDigest md = null;
		System.out.println(seed);
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int result = 0;
		boolean first = false, second = false;
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			md.update((seed + i).getBytes());

			byte[] pls = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int j = 0; j < pls.length; j++) {
				String hex = Integer.toHexString(0xff & pls[j]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			if (!first && hexString.toString().startsWith("00000")) {

				result = i;
				first = true;
			}
			if (!second && hexString.toString().startsWith("000000")) {

				result2 = i;
				second = true;
			}
			if (first && second)
				break;
		}
		return result;
	}

	@Override
	public Object secondPart(InputParser input) {
		if (result2 == 0) {
			firstPart(input);

		}
		return result2;
	}
}