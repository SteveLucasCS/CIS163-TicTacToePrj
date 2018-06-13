package package1;

import java.util.random;
import java.util.io.*;
import java.util.*;


// SuperAI
// David Whynot
// 6/6/18


public class SuperAI {

	private int[][] inputSpace;
	int outputRow;
	int outputCol;
	int weights[][];


	public SuperAI() {
		for(int x = 0; i < 3; ++i) {
			for(int y = 0; i < 3; ++i) {
				inputSpace[x][y] = 0;
			}
		}
	}

	public SuperAI(CellStatus[][] board) {
		this.board = board;
	}

	public SuperAI(CellStatus[][] board, String fPath) {
		this.board = board;
		loadWeights(fPath);
	}

	private void loadWeights(String fPath) {
		try {
			FileInputStream f = new FileInputStream(new File(fPath));
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String[] split = s.nextLine().split();
			}
		}
	}

	private void play(CellStatus[][] board) {

	}

	private void train() {

	}

	private void adjustWeights() {

	}
}



private class Layer {
	private Node[] nodes;
}


private class Node {
	private double value;
	private double activation;
	public Node() {

	}
}

private class InputNode {
	private double value;

}

private class OutputNode {
	private
}

private class Weight {

}
