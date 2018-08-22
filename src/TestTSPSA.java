package sample;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestTSPSA {
	private int numberOfNodes;
	private String cost_Function, search_Strategy;
	private int random_variable;
	private int cost =0;
	public int getcost() {

		return this.cost;
	}

	public void setcost(int value) {

		this.cost = value;

	}
	
	public int getRandom_variable() {
		return random_variable;
	}

	public void setRandom_variable(int random_variable) {
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

	
	public ArrayList generateIndividual(int numberOfNodes){
		//int[] individual = new int[numberOfNodes];
		ArrayList<Integer> individual = new ArrayList<Integer>(numberOfNodes);
		
		for(int i =0;i<numberOfNodes;i++){
			individual.add(i);
		}
		Collections.shuffle(individual);
		return individual;
		
		
	}
    public static double selection(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
	public double distanceToC1(int i, int j){
		double distance=0;
		if (i == j) {
			distance = 0;
		} else if (i < 3 && j < 3) {
			distance = 1;
		} else if (i < 3) {
			distance = 200;
		} else if (j < 3) {
			distance = 200;
		} else if ((i % 7) == (j % 7)) {
			distance = 2;
		} else {
			distance = abs(i - j) + 3;
		}
		return distance;
		
	}
	
	public double distanceToC2(int i, int j){
		double distance =0;
		if (i == j) {
			distance = 0;
		} else if (i + j < 10) {
			distance = abs(i - j) + 4;
		} else if (((i + j) % 11) == 0) {
			distance = 3;
		} else {
			distance = (int) (pow(abs(i - j), 2) + 10);
		}
		
		return distance;
	}
	public double distanceToC3(int i, int j){
		double distance =0;
		if (i == j) {
			distance = 0;
		} else {
			distance = (int) (pow(i + j, 2));
		}
		return distance;
	}
	public double gettingDistance(ArrayList<Integer> solution, String cost_Function) {
		//System.out.println("Entering sample.Node.distanceTo()");
		//System.err.println("Inside distanceTo");
		double distance = 0;



		switch (cost_Function) {
		case "C1":
			int i;
			for(i =0; i<solution.size()-1;i++){
				
				distance+=distanceToC1(solution.get(i),solution.get(i+1));
			}
			distance+=distanceToC1(solution.get(i),solution.get(0));
			

			//System.out.println("Distance using C1>>>" + distance);

			break;
		case "C2":
			
			for(i =0; i<solution.size()-1;i++){
				
				distance+=distanceToC2(solution.get(i),solution.get(i+1));
			}
			distance+=distanceToC1(solution.get(i),solution.get(0));
			

			//System.out.println("Distance using C2>>>" + distance);

			break;

		case "C3":
		
			for(i =0; i<solution.size()-1;i++){
				
				distance+=distanceToC3(solution.get(i),solution.get(i+1));
			}
			distance+=distanceToC1(solution.get(i),solution.get(0));
			
			//System.out.println("Distance using C3>>>" + distance);
			break;

		}

		//System.out.println("Exiting sample.Node.distanceTo()");
		return distance;
	}

	
	
	public void begin(){
		
        double temp = 200000;

        double coolingRate = 0.003;
		
		ArrayList<Integer> currentSolution = new ArrayList<Integer>(numberOfNodes);
		currentSolution = generateIndividual(numberOfNodes);
		
		ArrayList<Integer> bestSolution = new ArrayList<Integer>(numberOfNodes);
		bestSolution = currentSolution;
		
		Random rn = new Random();
		
		
		while (temp > 1) {

            ArrayList<Integer> newSolution = new ArrayList<Integer>(numberOfNodes);
            newSolution = currentSolution;
            int swapPos1 = rn.nextInt(newSolution.size());
            int swapPos2 = rn.nextInt(newSolution.size());
            int node1 = newSolution.get(swapPos1);
            int node2 = newSolution.get(swapPos2);
            newSolution.set(swapPos2, node1);
            newSolution.set(swapPos1, node2);
            double currentEnergy = gettingDistance(currentSolution,cost_Function);
            double neighbourEnergy = gettingDistance(newSolution,cost_Function);
            if (selection(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = newSolution;
            }

            if (gettingDistance(currentSolution,cost_Function) < gettingDistance(bestSolution,cost_Function)) {
                bestSolution = currentSolution;
            }

            temp *= 1-coolingRate;
        }
		
		cost = (int)gettingDistance(bestSolution,cost_Function);
		
        //System.out.println("Final solution distance: " + gettingDistance(bestSolution,cost_Function));
        //System.out.println("Route_Solution: " + bestSolution);
        //return cost;
	}
	
	
}
