/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;

/**
 *
 * @author priscilla
 */
public class ManageRoute {

	// Holds our cities
	private static ArrayList Nodes = new ArrayList<Node>();

	// Adds a destination city
	public static void addCity(Node city) {
		Nodes.add(city);
	}

	// Get a city
	public static Node getCity(int index) {
		return (Node) Nodes.get(index);
	}

	// Get the number of destination cities
	public static int numberOfNodes() {
		return Nodes.size();
	}
}