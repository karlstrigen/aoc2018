package Day8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {
	static ArrayList<Node> allNodes = new ArrayList<>();
	
	public static void main (String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("/home/karlstrigen/eclipse-workspace/AoC2018/src/Day8/input.txt"));
		String input = null;
		while (sc.hasNext()) {
			input = sc.nextLine();
		}
		sc.close();
		ArrayList<Integer> intList = createList(input);
		Node root = createNodes(intList);
		Integer sumMeta = getSumMeta();
		//Part 2
		Integer rootValue = nodeValue(root);
		System.out.println(sumMeta);
		System.out.println(rootValue);
		
	}

	private static Integer nodeValue(Node node) {
		int sum = 0;
		if (node.children.size() == 0) {
			for (int data : node.metadata)
				sum += data;
			return sum;
		}
		for (int data: node.metadata) {
			if (data == 0 || data > node.children.size())
				continue;
			Node debug = node.children.get(data - 1);
			sum += nodeValue(debug);
		}
		return sum;
	}

	private static Integer getSumMeta() {
		Integer sum = 0;
		for (Node node : allNodes) {
			for (Integer i : node.metadata) {
				sum += i;
			}
		}
		return sum;
	}

	private static Node createNodes(List<Integer> intList) {
		ArrayList<Integer> metaData = new ArrayList<>();
		Integer numberOfChildren = intList.remove(0);
		Integer numberOfMeta = intList.remove(0);
		ArrayList<Node> children = new ArrayList<>();
		for (int i = numberOfChildren; i>0; i--) {
			children.add(createNodes(intList));
		}
		for (int i = 0; i<numberOfMeta; i++) {
			metaData.add(intList.remove(0));
		}
		Node node = new Node(numberOfChildren, numberOfMeta, children, metaData); 
		allNodes.add(node);
		return node;
	}
	
	//Eclipse made me add this, parseInt was acting weird.
	@SuppressWarnings("deprecation")
	private static ArrayList<Integer> createList(String input) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		String [] array = input.split(" ");
		for (int i = 0; i<array.length; i++) {
			output.add(new Integer(Integer.parseInt(array[i])));
		}
		return output;
	}
	
	private static class Node {
		Integer header0;
		Integer header1;
		ArrayList<Node> children;
		List<Integer> metadata;
		
		private Node (Integer a, Integer b, ArrayList<Node> c, List<Integer> m) {
			header0 = a;
			header1 = b;
			children = c;
			metadata = m;
		}
	}

}
