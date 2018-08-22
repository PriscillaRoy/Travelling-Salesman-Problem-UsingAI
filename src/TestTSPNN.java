/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author priscilla
 */
import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TestTSPNN {
	private int numberOfNodes;
	private Stack<Integer> stack;
	private int cost_matrix[][];
	private int cost = 0;
	private String cost_Function, search_Strategy;
	private int[] tour_nn = new int[200];

	public TestTSPNN() {
		stack = new Stack<Integer>();
	}

	public int[] gettour() {

		return this.tour_nn;
	}

	public void settour_nn(int[] value) {

		this.tour_nn = value;
	}

	public int getnumberOfNodes() {

		return this.numberOfNodes;
	}

	public void setnumberOfNodes(int value) {

		this.numberOfNodes = value;
	}

	public String getcost_Function() {

		return this.cost_Function;
	}

	public void setcost_Function(String value) {

		this.cost_Function = value;
	}

	public String getsearch_Strategy() {

		return this.search_Strategy;
	}

	public void setsearch_Strategy(String value) {

		this.search_Strategy = value;
	}

	public int getcost() {
		// System.out.println("Cost from Test is : " + this.cost);
		return this.cost;
	}

	public void setcost(int value) {

		this.cost = value;
		// System.out.println("Cost from Test is : " + this.cost + "\n Value is
		// : " + value);
	}

	public void begin() {

		int costMatrix[][] = new int[numberOfNodes][numberOfNodes];
		TestTSPNN tspnn = new TestTSPNN();
		costMatrix = tspnn.CalculateCostMatrix(cost_Function, numberOfNodes);
		cost = tspnn.nearestNeighbor(costMatrix, numberOfNodes);
	}

	public int nearestNeighbor(int cost_matrix[][], int nodes) {

		int[] visited = new int[nodes];
		visited[0] = 1;
		stack.push(1);
		int element, dst = 0, i, last_ele = 1;
		ArrayList<Integer> myList = new ArrayList<Integer>();
		int min = Integer.MAX_VALUE;
		int first_ele = stack.peek();
		boolean minFlag = false;
		myList.add(0);
		// System.out.print(0 + "\t");

		while (!stack.isEmpty()) {
			element = stack.peek();
			i = 0;
			min = Integer.MAX_VALUE;
			while (i < nodes) {
				if (cost_matrix[element][i] > 0 && visited[i] == 0) {
					if (min > cost_matrix[element][i]) {
						min = cost_matrix[element][i];
						dst = i;
						minFlag = true;
					}
				}
				i++;
			}
			if (minFlag) {
				cost += cost_matrix[last_ele][dst];
				last_ele = dst;
				visited[dst] = 1;
				stack.push(dst);
				// System.out.print(dst + "\t");
				myList.add(dst);
				minFlag = false;
				continue;
			}
			stack.pop();
		}
		cost += cost_matrix[first_ele][last_ele];

		Arrays.fill(tour_nn, -1);
		for (int len = 0; len < nodes; len++) {

			tour_nn[len] = myList.get(len);

		}

		return cost;
	}

	public int[][] CalculateCostMatrix(String c, int number_of_nodes) {

		cost_matrix = new int[number_of_nodes + 1][number_of_nodes + 1];
		switch (c) {
		case "C1":
			for (int i = 0; i < number_of_nodes; i++) {
				for (int j = 0; j < number_of_nodes; j++) {
					if (i == j) {
						cost_matrix[i][j] = 0;
					} else if (i < 3 && j < 3) {
						cost_matrix[i][j] = 1;
					} else if (i < 3) {
						cost_matrix[i][j] = 200;
					} else if (j < 3) {
						cost_matrix[i][j] = 200;
					} else if ((i % 7) == (j % 7)) {
						cost_matrix[i][j] = 2;
					} else {
						cost_matrix[i][j] = abs(i - j) + 3;
					}
				}

			}
			break;
		case "C2":
			for (int i = 0; i < number_of_nodes; i++) {
				for (int j = 0; j < number_of_nodes; j++) {
					if (i == j) {
						cost_matrix[i][j] = 0;
					} else if (i + j < 10) {
						cost_matrix[i][j] = abs(i - j) + 4;
					} else if (((i + j) % 11) == 0) {
						cost_matrix[i][j] = 3;
					} else {
						cost_matrix[i][j] = (int) (pow(abs(i - j), 2) + 10);
					}
				}

			}
			break;

		case "C3":
			for (int i = 0; i < number_of_nodes; i++) {
				for (int j = 0; j < number_of_nodes; j++) {
					if (i == j) {
						cost_matrix[i][j] = 0;
					} else {
						cost_matrix[i][j] = (int) (pow(i + j, 2));
					}
				}

			}

		}

		return cost_matrix;
	}

}
