package Day5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class first {

	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day5/input.txt"));
		String input = sc.nextLine();
		sc.close();
		String answer = fixString(input);
		System.out.println(answer.length());
	}

	static String fixString(String input) {
		String string = input;
		int notChanged = 0;
		boolean skipNext = false;
		while (notChanged < string.length()-1) {
			notChanged = 0;
			skipNext = false;
			
			int test = string.length();
			ArrayList<Character> charList = new ArrayList<>();
			//System.out.println(string);
			for (int i = 0; i<string.length(); i++) {
				
				Character current = string.charAt(i);
				if (i == string.length()-1) {
					if (skipNext) {
						skipNext = false;
						break;
					}
					else {
						charList.add(current);
						break;
					}
				}
				Character next = string.charAt(i+1);
				if (skipNext) {
					skipNext = false;
				}
				else if (Character.isUpperCase(current)) {
					if ((Character.isLowerCase(next)) && (Character.toUpperCase(next) == current)) {
						notChanged = 0;
						skipNext = true;
					}
					else {
						notChanged++;
						charList.add(current);
					}
				}
				else if (Character.isLowerCase(current)) {
					if ((Character.isUpperCase(next)) && (Character.toLowerCase(next) == current)) {
						notChanged = 0;
						skipNext = true;
					}
					else {
						notChanged++;
						charList.add(current);
					}
				}
			}
			string = getStringRep(charList);
		}
		
		return string;
	}

	private static String getStringRep(ArrayList<Character> charList) {    
	    StringBuilder builder = new StringBuilder(charList.size());
	    for(Character ch: charList)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}


