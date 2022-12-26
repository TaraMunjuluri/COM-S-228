package edu.iastate.cs228.hw2;

/**
 *  
 * @author Tara Munjuluri
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		int trial=1;
		System.out.println("Performance of Four Sorting ALgorithms in Point Scanning");
		
		Scanner scnr= new Scanner(System.in);

		int input=0;
		PointScanner[] scanners = new PointScanner[4]; 

		while(input!=3) {
			System.out.println("Trial:"+trial);
			trial++;
			System.out.println("keys: 1(random integers) 2(file input) 3(exit)");
			input=scnr.nextInt();
		
			
			if(input==1) {
			
				System.out.println("Enter number of random points:");
				int userPts=scnr.nextInt();
				Random rand=new Random();
				Point[] points=generateRandomPoints(userPts,rand);
				
				scanners[0]=new PointScanner(points,Algorithm.InsertionSort);
				scanners[1]=new PointScanner(points,Algorithm.MergeSort);
				scanners[2]=new PointScanner(points,Algorithm.SelectionSort);
				scanners[3]=new PointScanner(points,Algorithm.QuickSort);
				for(int i=0;i<scanners.length;i++) {
					scanners[i].scan();
					System.out.println(scanners[i].toString());
				}
				System.out.println("algorithm size  time (ns) ");
				System.out.print("---------------------------------- \n");
				for(int i=0;i<scanners.length;i++) {
					System.out.println(scanners[i].stats());
				}
				System.out.print("---------------------------------- \n");

			}
			else if(input==2) {

				System.out.println("Points from file");
				System.out.println("File name:");
				String fname=scnr.next();
				
				scanners[0]=new PointScanner(fname,Algorithm.InsertionSort);
				
				scanners[1]=new PointScanner(fname,Algorithm.MergeSort);
				scanners[2]=new PointScanner(fname,Algorithm.SelectionSort);
				scanners[3]=new PointScanner(fname,Algorithm.QuickSort);
				for(int i=0;i<scanners.length;i++) {
					scanners[i].scan();
				}
				System.out.println("algorithm size  time (ns) ");
				System.out.print("---------------------------------- \n");
				for(int i=0;i<scanners.length;i++) {
					System.out.println(scanners[i].stats());
				}
				System.out.print("---------------------------------- \n");

				
			}
			
		}
		if(input!=1&&input!=2&&input!=3) {
			System.out.println("Enter another input");
			input=scnr.nextInt();
		}
		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		Point[] newAr= new Point[numPts];
		if(numPts>=1) {
		for(int i=0;i<numPts;i++) {
			int xPt=rand.nextInt(101)-50;
			int yPt=rand.nextInt(101)-50;
			newAr[i]=new Point(xPt,yPt);
		}
		}
		else {
			throw new IllegalArgumentException();
		}
		return newAr;
	}
		
	
}
