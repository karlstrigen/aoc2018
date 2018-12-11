package Day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class first {
	
	static ArrayList<TimeStamp> timeStamps = new ArrayList<>();
	static ArrayList<Guard> guards = new ArrayList<>();
	
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day4/input.txt"));
		timeStamps = sortTimes(sc);
		sc.close();
		timeStamps.sort(null);
		getSleepingTimes(timeStamps);
		System.out.println(getAnswer(guards));
		System.out.println(getSecondAnswer(guards));

		
	}
	
	private static int getSecondAnswer(ArrayList<Guard> guards2) {
		int maxSleep = 0;
		int maxId = 0;
		int minute = 0;
		for (Guard guard : guards2) {
			for (int i = 0; i<guard.minutes.length; i++) {
				if (guard.minutes[i] > maxSleep) {
					minute = i;
					maxSleep = guard.minutes[i];
					maxId = guard.id;
				}
			}
		}
		return minute * maxId;
	}

	private static int getAnswer(ArrayList<Guard> g) {
		int maxSleep = 0;
		int maxId = 0;
		int biggest = 0;
		int minute = 0;
		for (Guard guard : g) {
			int sum = 0;
			for(int i = 0; i<guard.minutes.length; i++) {
					sum += guard.minutes[i];
			}
			if (sum > maxSleep) {
				maxSleep = sum;
				maxId = guard.id;
				for (int i = 0; i<guard.minutes.length; i++) {
					if (guard.minutes[i] > biggest) {
						biggest = guard.minutes[i];
						minute = i;
					}
				}
			}
		}
		return maxId * minute;
	}

	private static void getSleepingTimes(ArrayList<TimeStamp> ts) {
		Guard currentGuard = new Guard(0);
		Integer[] tempArray = new Integer[60];
		Arrays.fill(tempArray, 0);
		int guardId = 0;
		
		
		for (int i = 0; i<ts.size(); i=i) {
			//System.out.println(i);
			
			int fallsAsleep = 0;
			int wakesUp = 0;
			if (ts.get(i).info.startsWith("Guard")) {
				String temp = ts.get(i).info.substring(ts.get(i).info.indexOf(" "));
				temp = temp.substring(2, temp.indexOf("b")-1);
				guardId = Integer.parseInt(temp);

				currentGuard = new Guard(guardId);
				if (!(guards.contains(currentGuard))) {
					guards.add(currentGuard);
				}
				else {
					for (Guard g : guards) {
						if (g.id == guardId) {
							currentGuard = g;
						}
					}
				}
				
				fallsAsleep = ts.get(i+1).minutes;
				wakesUp = ts.get(i+2).minutes;
				i = i + 3;
			}
			else {
				fallsAsleep = ts.get(i).minutes;
				wakesUp = ts.get(i+1).minutes;
				i = i + 2;
			}
			for (int j = fallsAsleep; j<wakesUp; j++) {
				currentGuard.minutes[j]++;
				
			}
		}
		
	}

	private static ArrayList<TimeStamp> sortTimes(Scanner sc){
		ArrayList<TimeStamp> returnArray = new ArrayList<>();
		while (sc.hasNext()) {
			String currentLine = sc.nextLine();
			String copy = currentLine;
			copy = copy.substring(1, copy.indexOf("]"));
			currentLine = currentLine.substring(currentLine.indexOf("]") + 2);
			TimeStamp current = new TimeStamp(copy, currentLine);
			returnArray.add(current);
		}
		return returnArray;
	}
	
	private static class Guard {
		Integer id;
		Integer[] minutes = new Integer[60];
		
		public Guard(int i) {
			id = i;
			Arrays.fill(minutes, 0);
		}

		public Guard(int i, Integer[] tempArray) {
			id = i;
			minutes = tempArray;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Guard other = (Guard) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
	}
	
	private static class TimeStamp implements Comparable<TimeStamp>{
		Double orderBy;
		String time;
		String info;
		Integer minutes;
		
		public TimeStamp(String timeIn, String infoIn) {
			time = timeIn;
			info = infoIn;
			orderBy = getOrder(timeIn);
			minutes = getMinutes(timeIn);
		}
		private Integer getMinutes(String timeIn) {
			String[] tempNumsString = timeIn.split("[- ]");
			String[] clock = tempNumsString[3].split("[:]");
			return Integer.parseInt(clock[1]);
		}
		private Double getOrder(String timeIn) {
			String[] tempNumsString = timeIn.split("[- ]");
			String[] clock = tempNumsString[3].split("[:]");
			
			return  1000 * Integer.parseInt(tempNumsString[0])+
					100 * Integer.parseInt(tempNumsString[1])+
					Integer.parseInt(tempNumsString[2])+
					0.001 * (10*Integer.parseInt(clock[0])
							+ Integer.parseInt(clock[1]));	
		}

		@Override
		public int compareTo(TimeStamp arg0) {
			return Double.compare(this.orderBy, arg0.orderBy);
		}
		
		
	}
}
