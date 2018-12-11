package Day3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class first {
	static final Integer SIZE = 1000;
	static Integer [][] result = new Integer[SIZE][SIZE];
	
	static ArrayList<Integer[]> claims = new ArrayList<>();
	
	static ArrayList<Integer> firstIntegers = new ArrayList<>();
	static ArrayList<Integer> secondIntegers = new ArrayList<>();
	static ArrayList<Integer> thirdIntegers = new ArrayList<>();
	static ArrayList<Integer> fourthIntegers = new ArrayList<>();
	static ArrayList<Integer> fifthIntegers = new ArrayList<>();
	
	public static void main (String args[]) throws FileNotFoundException {
		initResult();
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day3/input.txt"));
		while (sc.hasNext()) {
			fixInput(sc.nextLine());
			putOccupied();
			fixLists();
		}
		sc.close();
		Integer squaresOverlapping = getResult();
		Integer id = secondTask();
		System.out.println(squaresOverlapping);
		System.out.println(id);
		
		
	}




	private static Integer secondTask() {
		boolean foundClaim = false;
		for (Integer[] claim : claims) {
			Integer id = claim[4];
			Integer fromLeft = claim[0];
			Integer fromTop = claim[1];
			Integer width = claim[2];
			Integer height = claim[3];
			
			outerloop :
			for (int outer = 0; outer<height; outer++) {
				for (int inner = 0; inner<width; inner++) {
					if (!(result[fromTop + outer][fromLeft + inner]==1)) {
						break outerloop;
					}
					else if (outer == height - 1 && inner == width - 1) {
						return id;
					}
				}
			}
			
		}
		return null;
		
	}




	private static Integer getResult() {
		Integer returnValue = 0;
		for (int i = 0; i<SIZE; i++) {
			for (int j = 0; j<SIZE; j++) {
				if (result[i][j] == 2) {
					returnValue++;
				}
			}
		}
		return returnValue;
	}


	private static void putOccupied() {
		Integer[] integers = new Integer[5];
		integers = getIntegers();
		Integer id = integers[4];
		Integer fromLeft = integers[0];
		Integer fromTop = integers[1];
		Integer width = integers[2];
		Integer height = integers[3];
		
		claims.add(integers);
		
		for (int outer = 0; outer<height; outer++) {
			for (int inner = 0; inner<width; inner++) {
				if (result[fromTop + outer][fromLeft + inner]<2) {
					result[fromTop + outer][fromLeft + inner]++;
				}
			}
		}
		return;
	}
	
	private static void initResult() {
		for(int i = 0; i<SIZE; i++) {
			for (int j = 0; j<SIZE; j++) {
				result[i][j] = 0;
			}
		}
		
	}


	private static void fixInput(String string) {
		String finalOne = string.replaceAll("[^0-9]", " ");
		Integer numberCount = 0;
		for (int i = 0; i<finalOne.length(); i++) {
			if (!(finalOne.charAt(i) == ' ')) {
				if(numberCount == 0) {
					firstIntegers.add(Character.getNumericValue(finalOne.charAt(i)));
				}
				else if (numberCount == 1) {
					secondIntegers.add(Character.getNumericValue(finalOne.charAt(i)));
				}
				else if (numberCount == 2) {
					thirdIntegers.add(Character.getNumericValue(finalOne.charAt(i)));
				} 
				else if (numberCount == 3) {
					fourthIntegers.add(Character.getNumericValue(finalOne.charAt(i)));
				} 
				else if (numberCount == 4) {
					fifthIntegers.add(Character.getNumericValue(finalOne.charAt(i)));
				} 
			}
			if (i == finalOne.length()-1) {
				return;
			}
			if (!(finalOne.charAt(i) == ' ')&&finalOne.charAt(i+1) == ' ') {
				numberCount++;
				
			}
			
		}
		
		
	}


	private static Integer[] getIntegers() {

		Double[] integers = new Double[5];
		Arrays.fill(integers, 0.0);
		Integer [] returnI = new Integer[5];
		for (int i = 0; i<secondIntegers.size(); i++) {
			integers[0] += secondIntegers.get(i) * Math.pow(10, secondIntegers.size()-1-i); 
		}
		for (int i = 0; i<thirdIntegers.size(); i++) {
			integers[1] += thirdIntegers.get(i) * Math.pow(10, thirdIntegers.size()-1-i); 
		}
		for (int i = 0; i<fourthIntegers.size(); i++) {
			integers[2] += fourthIntegers.get(i) * Math.pow(10, fourthIntegers.size()-1-i); 
		}
		for (int i = 0; i<fifthIntegers.size(); i++) {
			integers[3] += fifthIntegers.get(i) * Math.pow(10, fifthIntegers.size()-1-i); 
		}
		for (int i = 0; i<firstIntegers.size(); i++) {
			integers[4] += firstIntegers.get(i) * Math.pow(10, firstIntegers.size()-1-i); 
		}
		for (int i = 0; i<integers.length; i++) {
			returnI[i] = integers[i].intValue();
		}
		return returnI;
	}
	private static void fixLists() {
		firstIntegers = new ArrayList<>();
		secondIntegers = new ArrayList<>();
		thirdIntegers = new ArrayList<>();
		fourthIntegers = new ArrayList<>();
		fifthIntegers = new ArrayList<>();
		
	}
}
