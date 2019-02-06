package Day7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class second {
static ArrayList<Step> steps = new ArrayList<>();
	static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day7/input.txt"));
		
		while (sc.hasNext()) {
			putSteps(sc.nextLine());
		}
		int result = exeStepsWorkers();
		System.out.println(result);
	}
	
	
	private static int exeStepsWorkers() {
		boolean[] workers = new boolean[5];
		Step[] stepsInProgress = new Step[5];
		int count = 0;
		for (boolean b : workers) 
			b = false;
		
		while(!steps.isEmpty()) {
			assignSteps(workers, stepsInProgress);
			workOnSteps(workers, stepsInProgress);
			count++;
		}
		return count;
	}

	private static void workOnSteps(boolean[] workers, Step[] stepsInProgress) {
		for (int i = 0; i<stepsInProgress.length; i++) {
			if (stepsInProgress[i] == null)
				continue;
			stepsInProgress[i].timeToFinish--;
			if (stepsInProgress[i].timeToFinish == 0) {
				stepsInProgress[i].finished = true;
				steps.remove(stepsInProgress[i]);
				workers[i] = false;
				System.out.println(stepsInProgress[i].name);
			}
		}
	}

	private static void assignSteps(boolean[] workers, Step[] stepsInProgress) {
		for (int i = 0; i<workers.length; i++) {
			if (!workers[i]) {
				Step s = getReadyStep();
				stepsInProgress[i] = s;
				if (s!= null)
					workers[i] = true;
			}
		}
	}

	private static Step getReadyStep() {
		//To make the comparison work I set a comparator to Z initially.
		String comparator = "Z";
		Step output = null;
		for (Step s : steps) {
			if (s.noPrereqs() && !s.executing) {
				s.executing = true;
				comparator = s.name;
				return s;
				//output = s;
			}
		}
		return output;
	}
	

	static void putSteps(String input) {
		String [] parts = input.split(" ");
		String step = parts[7];
		String prerequisite = parts[1];
		putStepPrer(step, prerequisite);
	}
	
	private static void putStepPrer(String step, String prerequisite) {
		Step pre = getPre(prerequisite);
		for (Step s : steps) {
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
	
	public static class Step {
		String name;
		ArrayList<Step> prerequisites;
		boolean finished; 
		boolean executing;
		int timeToFinish;
		
		Step (String x) {
			name = x;
			prerequisites = new ArrayList<>();
			finished = false;
			executing = false;
			timeToFinish = 60 + alphabet.indexOf(x) + 1;
		}

		public boolean noPrereqs() {
			if (prerequisites.size() == 0) {
				return true;
			}
			else {
				for (Step s : prerequisites) {
					if (!s.finished) 
						return false;
				}
				return true;
			}
		}		
	}

		
}
