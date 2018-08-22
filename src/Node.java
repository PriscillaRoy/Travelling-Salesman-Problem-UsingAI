/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 *
 * @author priscilla
 */
public class Node {

	private int city_number;

	public int getcity_number() {

		return this.city_number;
	}

	public void setcity_number(int value) {

		this.city_number = value;
	}

	public double distanceTo(Node city, String cost_Function) {

		double distance = 0;
		int i = getcity_number();
		int j = city.getcity_number();

		switch (cost_Function) {
		case "C1":
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

			break;
		case "C2":
			if (i == j) {
				distance = 0;
			} else if (i + j < 10) {
				distance = abs(i - j) + 4;
			} else if (((i + j) % 11) == 0) {
				distance = 3;
			} else {
				distance = (int) (pow(abs(i - j), 2) + 10);
			}

			break;

		case "C3":
			if (i == j) {
				distance = 0;
			} else {
				distance = (int) (pow(i + j, 2));
			}

			break;

		}

		return distance;
	}

}
