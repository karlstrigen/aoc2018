package Day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class first {
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day6/input.txt"));
		ArrayList<Position> positions = new ArrayList<>();
		while (sc.hasNext()) {
			Position temp = posFromInput(sc.nextLine());
			positions.add(temp);
		}
		sc.close();
		getResult(positions);
		//System.out.println(area);
	}
	
	

	private static void getResult(ArrayList<Position> positions) {
		int maxX = getMaxX(positions) + 1;
		int maxY = getMaxY(positions) + 1;
		int minX = getMinX(positions) - 1;
		int minY = getMinY(positions) - 1;
		
		int partTwoAnswer = 0;
		
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y<= maxY; y++) {
				Position closest = getClosestPosition(x ,y, positions);
				if (closest != null) {
					closest.closestPoints++;
					if (x == minX || x == maxX || y == minY || y == maxY) 
						closest.infiniteArea = true;
				}
				//Part 2
				int totalDistance = getTotalDistance(x, y, positions);
				if (totalDistance < 10000)
					partTwoAnswer++;
			}
		}
		int maxArea = 0;
		for (Position p : positions) {
			if (p.closestPoints > maxArea && !p.infiniteArea) {
				maxArea = p.closestPoints;
			}
		}
		System.out.println(maxArea);
		System.out.println(partTwoAnswer);
		//return maxArea;
	}

	private static int getTotalDistance(int x, int y, ArrayList<Position> positions) {
		int totalDist = 0;
		for (Position p : positions) {
			totalDist += Math.abs(x - p.x) + Math.abs(y - p.y);
		}
		return totalDist;
	}



	private static Position getClosestPosition(int x, int y, ArrayList<Position> positions) {
		int closestDistance = Integer.MAX_VALUE;
		int secondDistance = Integer.MAX_VALUE;
		
		Position returnP = new Position(0,0);
		for (Position p : positions) {
			//Taxicab distance
			int tempDist = Math.abs(x - p.x) + Math.abs(y - p.y);
			if (tempDist < closestDistance) {
				closestDistance = tempDist;
				returnP = p;
			}
			else if (tempDist < secondDistance) {
				secondDistance = tempDist;
			}
		}
		if (closestDistance == secondDistance) 
			return null;
		return returnP;
	}

	private static int getMinY(ArrayList<Position> positions) {
		int min = Integer.MAX_VALUE;
		for (Position p : positions) {
			if (p.y < min) {
				min = p.y;
			}
		}
		return min;
	}

	private static int getMinX(ArrayList<Position> positions) {
		int min = Integer.MAX_VALUE;
		for (Position p : positions) {
			if (p.x < min) {
				min = p.x;
			}
		}
		return min;
	}
	
	private static int getMaxY(ArrayList<Position> positions) {
		int max = 0;
		for (Position p : positions) {
			if (p.x > max) {
				max = p.x;
			}
		}
		return max;
	}

	private static int getMaxX(ArrayList<Position> positions) {
		int max = 0;
		for (Position p : positions) {
			if (p.y > max) {
				max = p.y;
			}
		}
		return max;
	}

	private static Position posFromInput(String string) {
		String [] parts = string.split(", ");
		return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}

	private static class Position {
		int x;
		int y;
		int closestPoints;
		boolean infiniteArea;
		
		public Position (int xIn, int yIn) {
			x = xIn;
			y = yIn;
			closestPoints = 0;
			infiniteArea = false;
		} 
	}
}
