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
public class Population {

	// Holds population of tours
	public Route_Solution[] tours;

	// Construct a population
	public Population(int numberOfCities, int populationSize, boolean initialise) {

		tours = new Route_Solution[populationSize];

		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < populationSize; i++) {
				Route_Solution newTour = new Route_Solution();
				newTour.generateIndividual(numberOfCities);
				saveTour(i, newTour);
			}
		} else {

		}
	}

	// Saves a tour
	public void saveTour(int index, Route_Solution tour) {
		tours[index] = tour;
	}

	// Gets a tour from population
	public Route_Solution getTour(int index) {
		return tours[index];
	}

	// Gets the best tour in the population
	public Route_Solution getFittest(String cost_Function) {
		Route_Solution fittest = tours[0];
		// Loop through individuals to find fittest
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness(cost_Function) <= getTour(i).getFitness(cost_Function)) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}

	// Gets population size
	public int populationSize() {
		return tours.length;
	}
}