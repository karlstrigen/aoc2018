package Day7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class first {
	static ArrayList<Step> steps = new ArrayList<>();
	
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day7/input.txt"));
		
		while (sc.hasNext()) {
			putSteps(sc.nextLine());
		}
		String output = buildOutput();
		System.out.println(output);
	}

	private static String buildOutput() {
		String result = "";
		while (result.length() < steps.size()) {
			String temp = getReadyStep();
			result = result + temp;
		}
		return result;
	}

	private static String getReadyStep() {
		//To make the comparison work I set output to Z initially.
		String output = "Z";
		for (Step s : steps) {
			if (s.noPrereqs() && !s.taken && s.name.charAt(0) < output.charAt(0)) {
				output = s.name;
			}
		}
		for (Step s : steps) {
			if (s.name.equals(output))
				s.taken = true;
		}
		return output;
	}

	private static void putSteps(String input) {
		String [] parts = input.split(" ");
		String step = parts[7];
		String prerequisite = parts[1];
		putStepPrer(step, prerequisite);
	}
	
	private static void putStepPrer(String step, String prerequisite) {
		Step pre = getPre(prerequisite);
		for (Step s :steps) {
			if (s.name.equals(step)) {
				s.prerequisites.add(pre);
				return;
			}
		}
		Step s = new Step(step);
		s.prerequisites.add(pre);
		steps.add(s);
	}

	private static Step getPre(String prerequisite) {
		for (Step s: steps) {
			if (s.name.equals(prerequisite)) {
				return s;
			}
		}
		Step s = new Step(prerequisite);
		steps.add(s);
		return s;
	}

	private static class Step {
		String name;
		ArrayList<Step> prerequisites;
		boolean taken; 
		
		private Step (String x) {
			name = x;
			prerequisites = new ArrayList<>();
			taken = false;
		}

		public boolean noPrereqs() {
			if (prerequisites.size() == 0) {
				return true;
			}
			else {
				for (Step s : prerequisites) {
					if (!s.taken) 
						return false;
				}
				return true;
			}
		}		
	}
}
