package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Tara Munjuluri
 *
 * The ISPBusiness class performs simulation over a grid plain with
 * cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int casuals = 0;
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					casuals++;
				}
			}
		}
		return casuals;

	}

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements
	 * of grid via an input file (option: 1) or wants to generate it randomly
	 * (option: 2).
	 * 
	 * Depending on the user choice, create the Town object using respective
	 * constructor and if user choice is to populate it randomly, then populate the
	 * grid here.
	 * 
	 * Finally: For 12 billing cycle calculate the profit and update town object
	 * (for each cycle). Print the final profit in terms of %. You should print the
	 * profit percentage with two digits after the decimal point: Example if profit
	 * is 35.5600004, your output should be:
	 *
	 * 35.56%
	 * 
	 * Note that this method does not throw any exception, so you need to handle all
	 * the exceptions in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly \n" + "with seed");
		Town t = null;
		int i = 0;
		double profit = 0;
		double percent = 0;
		int userInput = scnr.nextInt();
		if (userInput == 1) {
			System.out.println("Please enter file path:");
			String file = scnr.next();
			try {
				t = new Town(file);
				

				profit += getProfit(t);
		 } catch (FileNotFoundException f) {
				System.out.println("File not found");
			}
		} else if(userInput==2) {
			System.out.println("Provide rows, cols and seed integer separated by spaces: ");
			int userCol = scnr.nextInt();
			int userRow = scnr.nextInt();
			int seed = scnr.nextInt();
			t = new Town(userRow, userCol);
			t.randomInit(seed);
			

			
			profit += getProfit(t);

		}
		
		for (int k = 1; k < 12; k++) {
			t = updatePlain(t);
			

			profit += getProfit(t);
		}
		percent = (profit * 100) / (t.getLength() * t.getWidth() * 12);
		System.out.printf("%.2f%%%n", percent);

		scnr.close();
	}

}
