import java.util.*;

//This solution is very slow, 
public class second {
	static boolean found = false;
	static ArrayList<Integer> frequencies = new ArrayList<Integer>();
	
	public static void main(String args[]) {
		int currentValue = 0;
		int iterations = 0;
		while (!found) {
			currentValue = findFreq(args, currentValue);
		}
		
	}
	
	private static int findFreq (String[] args, int currentValue) {
		int update = currentValue;
		for (int i = 0; i<args.length; i++) {
			frequencies.add(update);
			update += getAdd(args[i]);
			if (frequencies.contains(update)) {
				found = true;
				System.out.println(update);
				return update;
			}
		}
		return update;
	}

	private static int getAdd(String string) {
		if (string.charAt(0) == '+') {
			return Integer.parseInt(string.substring(1));	
		}
		else if (string.charAt(0) == '-') {
			return -(Integer.parseInt(string.substring(1)));
		}
		else throw new RuntimeException("Wrong input format");
	}
}
