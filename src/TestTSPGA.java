/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author priscilla
 */
public class TestTSPGA {

	private int numberOfNodes;

	private int cost_matrix[][];
	private int cost = 0;
	private String cost_Function, search_Strategy;
	private int meb_variable;
	private int random_variable;
	private int[] tour_ga = new int[200];

	public int getmeb_variable() {
		return meb_variable;
	}

	public void settour(int[] tour) {
		this.tour_ga = tour;
	}

	public int[] gettour() {
		return tour_ga;
	}

	public void setmeb_variable(int meb_variable) {
		this.meb_variable = meb_variable;
	}

	public int getrandom_variable() {
		return random_variable;
	}

	public void setrandom_variable(int random_variable) {
		this.random_variable = random_variable;
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

		return this.cost;
	}

	public void setcost(int value) {

		this.cost = value;

	}

	public void begin() {

		Population pop = new Population(numberOfNodes, meb_variable, true);

		for (int i = 0; i < 2000; i++) {
			pop = AlgoGenetic.evolvePopulation(numberOfNodes, pop, cost_Function);
		}

		cost = pop.getFittest(cost_Function).getDistance(cost_Function);
		Route_Solution t = pop.getFittest(cost_Function);
		int[] temp_tour = null;
		ArrayList<Integer> myList = new ArrayList<Integer>();
		for (int i = 0; i < t.tourSize(); i++) {

			int j = t.getCity(i).getcity_number();
			myList.add(j);
		}

		Arrays.fill(tour_ga, -1);
		for (int i = 0; i < t.tourSize(); i++) {

			tour_ga[i] = myList.get(i);
		}

	}
}
