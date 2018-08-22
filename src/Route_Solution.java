/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author priscilla
 */
public class Route_Solution {

	private ArrayList<Node> tour = new ArrayList<Node>();

	private double fitness = 0;
	private int distance = 0;
	String cost_function = null;
	private int total_cost = 0;

	public Route_Solution(int numberOfNodes) {
		Node c = new Node();
		c.setcity_number(numberOfNodes + 7);
		for (int i = 0; i < numberOfNodes; i++) {
			tour.add(c);
		}
	}

	public Route_Solution() {

		for (int i = 0; i < ManageRoute.numberOfNodes(); i++) {
			tour.add(null);
		}
	}

	public ArrayList getTour() {
		return tour;
	}

	public String getCost_function() {
		return cost_function;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTour(ArrayList tour) {
		this.tour = tour;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setCost_function(String cost_function) {
		this.cost_function = cost_function;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	public Route_Solution(ArrayList tour) {
		this.tour = tour;
	}

	public void generateIndividual(int numberOfCities) {
		int[] visited = new int[10];
		Arrays.fill(visited, 0);
		for (int i = 0; i < numberOfCities; i++) {
			Node c = new Node();
			c.setcity_number(i);
			setCity(i, c);
		}
		// Randomly reorder the tour
		Collections.shuffle(tour);
	}

	public Node getCity(int tourPosition) {
		return (Node) tour.get(tourPosition);

	}

	public void setCity(int tourPosition, Node city) {
		tour.add(tourPosition, city);
		fitness = 0;
		distance = 0;

	}

	// Gets the tours fitness
	public double getFitness(String cost_Function) {

		if (fitness == 0) {
			fitness = 1 / (double) getDistance(cost_Function);
		}
		return fitness;
	}

	// Gets the total distance of the tour
	public int getDistance(String cost_Function) {
		if (distance == 0) {
			int tourDistance = 0;
			for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
				Node fromCity = getCity(cityIndex);
				Node destinationCity;

				if (cityIndex + 1 < tourSize()) {
					destinationCity = getCity(cityIndex + 1);
				} else {

					destinationCity = getCity(0);
				}
				tourDistance += fromCity.distanceTo(destinationCity, cost_Function);

			}
			distance = tourDistance;
		}
		return distance;
	}

	public int tourSize() {

		return tour.size();
	}

	public boolean containsCity(Node city) {
		return tour.contains(city);
	}

	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < tourSize(); i++) {
			geneString += getCity(i) + "|";
		}
		return geneString;
	}
}