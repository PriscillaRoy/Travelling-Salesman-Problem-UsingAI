/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Random;

/**
 *
 * @author Priscilla
 */
public class AlgoGenetic {
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;

	public static Population evolvePopulation(int numberOfCities, Population pop, String cost_Function) {

		Population newPopulation = new Population(numberOfCities, pop.populationSize(), false);
		newPopulation.saveTour(0, pop.getFittest(cost_Function));

		Route_Solution parent1 = new Route_Solution();
		Route_Solution parent2 = new Route_Solution();
		for (int i = 1; i < newPopulation.populationSize(); i++) {

			parent1 = selecting_Tournament(numberOfCities, pop, cost_Function);
			parent2 = selecting_Tournament(numberOfCities, pop, cost_Function);

			Route_Solution child = twoPointCrossover(parent1, parent2);

			newPopulation.saveTour(i, child);
		}

		// Mutate the new population a bit to add some new genetic material
		/*
		 * Route_Solution[] temp_tour; temp_tour = new
		 * Route_Solution[newPopulation.populationSize()]; for (int i = 0; i <
		 * newPopulation.populationSize(); i++) {
		 * 
		 * temp_tour[i] = newPopulation.getTour(i); temp_tour[i]
		 * =mutate(temp_tour[i]); newPopulation.saveTour(i,temp_tour[i]) ; }
		 */
		// System.out.println("Exiting sample.AlgoGenetic.evolvePopulation()");
		return newPopulation;
	}

	public static Route_Solution twoPointCrossover(Route_Solution parent1, Route_Solution parent2) {

		Route_Solution child = new Route_Solution();

		Random rn = new Random();
		int startpos = rn.nextInt(parent1.tourSize());
		int endpos = rn.nextInt(parent1.tourSize());

		int[] child_temp = new int[parent1.tourSize()];
		Arrays.fill(child_temp, parent1.tourSize() + 7);
		if (startpos == endpos) {
			if (startpos == 0) {
				endpos++;
			} else {
				endpos--;
			}

		}
		for (int i = 0; i < parent1.tourSize(); i++) {

			if (startpos < endpos) {
				if (i >= startpos && i <= endpos) {
					child_temp[i] = parent1.getCity(i).getcity_number();
				}
			} else if (startpos > endpos) {
				if (!(i < startpos && i > endpos)) {

					child_temp[i] = parent1.getCity(i).getcity_number();
				}
			}

		}
		for (int i = 0; i < parent2.tourSize(); i++) {
			int m = parent2.getCity(i).getcity_number();

			if (!(doesItContain(child_temp, m)))

			{
				for (int l = 0; l < parent2.tourSize(); l++) {
					int n = parent2.tourSize() + 7;
					if (child_temp[l] == n) {

						child_temp[l] = m;
						break;
					}
				}
			}

		}

		for (int k = 0; k < parent1.tourSize(); k++) {
			Node c = new Node();
			c.setcity_number(child_temp[k]);
			child.setCity(k, c);
		}

		return child;
	}

	private static boolean doesItContain(int[] array, int value) {

		boolean var = false;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				var = true;
				break;

			} else
				var = false;
		}
		return var;
	}

	// Mutate a tour using swap mutation
	private static Route_Solution Mutation(Route_Solution tour_mutate) {

		Random rn = new Random();
		int do_mutate = rn.nextInt(2);
		if (do_mutate == 1) {
			int swap_city1 = rn.nextInt(tour_mutate.tourSize());
			int swap_city2 = rn.nextInt(tour_mutate.tourSize());
			if (swap_city1 != swap_city2) {

				Node city1 = tour_mutate.getCity(swap_city1);
				Node city2 = tour_mutate.getCity(swap_city2);

				tour_mutate.setCity(swap_city2, city1);
				tour_mutate.setCity(swap_city1, city2);

			}
		}
		return tour_mutate;

	}

	private static Route_Solution selecting_Tournament(int numberOfCities, Population pop, String cost_Function) {

		Population tournament = new Population(numberOfCities, tournamentSize, false);

		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.saveTour(i, pop.getTour(randomId));
		}

		Route_Solution fittest = tournament.getFittest(cost_Function);

		return fittest;
	}
}
