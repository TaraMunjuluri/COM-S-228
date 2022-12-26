package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Tara MUnjuluri
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length=length;
		this.width=width;
        grid=new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {		
		File file= new File("src/"+inputFileName);
		Scanner scnr= new Scanner(file);
		int rowCounter=0;
		int colCounter=0;
	
			length=Integer.parseInt(scnr.next());
			width=Integer.parseInt(scnr.next());
			grid=new TownCell[length][width];
	
		
		while(scnr.hasNext()) { 
			String letter=scnr.next();
			if(letter.equals("O")) {
				grid[rowCounter][colCounter]=new Outage(this,rowCounter,colCounter);
			}
			else if(letter.equals("R")) {
				grid[rowCounter][colCounter]=new Reseller(this,rowCounter,colCounter);	
			}
			else if(letter.equals("C")) {
				grid[rowCounter][colCounter]=new Casual(this,rowCounter,colCounter);	
			}
			else if(letter.equals("S")) {
				grid[rowCounter][colCounter]=new Streamer(this,rowCounter,colCounter);	
			}
			else if(letter.equals("E")) {
				grid[rowCounter][colCounter]=new Empty(this,rowCounter,colCounter);	
			}
			colCounter++;
			
			if(colCounter==width) {
				colCounter=0;
				rowCounter++;
			}
		}
		scnr.close();
	}	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);

				for(int i=0;i<length;i++) {
					for(int j=0; j<width;j++) {
						int random=rand.nextInt(5);
						if(random==TownCell.RESELLER) {
							grid[i][j]=new Reseller(this,i,j);
						}
						else if(random==TownCell.OUTAGE) {
							grid[i][j]=new Outage(this,i,j);
						}
						else if(random==TownCell.CASUAL) {
							grid[i][j]=new Casual(this,i,j);
						}
						else if(random==TownCell.EMPTY) {
							grid[i][j]=new Empty(this,i,j);
						}
						else if(random==TownCell.STREAMER) {
							grid[i][j]=new Streamer(this,i,j);
						}
					}
				}
			}
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		
		String s = "";
		int counter=0;
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++) {
				if(grid[i][j].who() == State.RESELLER) {
					s+="R";
				}
				else if(grid[i][j].who()==State.OUTAGE) {
					s+="O";
				}
				else if(grid[i][j].who()==State.CASUAL) {
					s+="C";
				}
				else if(grid[i][j].who()==State.EMPTY) {
					s+="E";
				}
				else if(grid[i][j].who()==State.STREAMER) {
					s+="S";
				}
				
				counter++;
				if(counter==length) {
					counter=0;
					s+="\n";
				}
				else {
					s+="\t";
				}
			}
				
			}
		return s.substring(0,s.length()-1);
	}
}
