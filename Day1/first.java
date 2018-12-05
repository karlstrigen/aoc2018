import java.util.ArrayList;

public class first {
	
	public static void main(String args[]) {
		int start = 0;
		for (int i = 0; i<args.length; i++) {
				start += getAdd(args[i]);
		}
		System.out.println(start);
		
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
