package Day2;

import java.util.*;

public class first {
	
	
	public static void main(String args[]) {
		int twos = 0;
		int threes = 0;
		Boolean[] results;
		for (int i = 0; i<args.length; i++) {
			results = getChecksum(args[i]);
			if(results[0]) {
				twos++;
			}
			if (results[1]) {
				threes++;
			}
		}
		int result = twos * threes;
		System.out.println(result);
		
		
	}

	private static Boolean[] getChecksum(String string) {
		Boolean[] twosAndThrees = new Boolean[2];
		HashMap<Character,Integer> appearances = new HashMap<>();
		for(int i = 0; i<string.length(); i++) {
			char currentChar = string.charAt(i);
			if (!appearances.containsKey(currentChar)) {
				appearances.put(currentChar, 1);
			}
			else {
				int times = appearances.get(currentChar);
				appearances.put(currentChar, times + 1);
			}
		}
		twosAndThrees[0]=appearances.containsValue(2);
		twosAndThrees[1]=appearances.containsValue(3);
		return twosAndThrees;
	}
}
