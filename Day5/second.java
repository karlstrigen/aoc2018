package Day5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class second {
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day5/input.txt"));
		String input = sc.nextLine();
		sc.close();
		int answer = fixString(input);
		System.out.println(answer);
	}

	private static int fixString(String input) {
		int bestResult = Integer.MAX_VALUE;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		String string = input;
		for (char c : alphabet) {
			char upper = Character.toUpperCase(c);
			
			ArrayList<Character> charList = new ArrayList<>();
			for (int i = 0; i<input.length(); i++) {
				if (!(input.charAt(i) == c || input.charAt(i) == upper)) {
					charList.add(input.charAt(i));
				}
			}
			string = getStringRep(charList);
			string = first.fixString(string);
			if (string.length() < bestResult) {
				bestResult = string.length();
			}
			
		}
		return bestResult;
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
