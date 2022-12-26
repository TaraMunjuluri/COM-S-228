package edu.iastate.cs228.hw4;

import java.io.File;
/**
 * Main method 
 * @author Tara Munjuluri
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
/*
 * Main method 
 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Please enter filename to decode: ");
        Scanner scnr = new Scanner(System.in);
		String file=scnr.next();
		System.out.println("Character     Code\n-------------------------");

		reader(file);
		
		
	}
	/*
	 * Helper method that reads the file
	 */
	public static void reader(String inputFileName) throws FileNotFoundException {
		String encoding="";

		File newfile = new File("src/" + inputFileName);
		Scanner scnr = new Scanner(newfile);
		

		int count = 0;
		String message="";

		while (scnr.hasNextLine()) {
		scnr.nextLine();
			count++;
			
		}
		Scanner s = new Scanner(newfile);

		if (count == 2) {
			encoding = s.nextLine();
			message=s.nextLine();
			
		} else {
			encoding=s.nextLine()+'\n';
			encoding+=s.nextLine();
			message=s.nextLine();
		
		}
		scnr.close();
		s.close();

		MsgTree tree = new MsgTree(encoding);

		
	MsgTree.printCodes(tree, "");
	tree.decode(tree,message);

}
	}
