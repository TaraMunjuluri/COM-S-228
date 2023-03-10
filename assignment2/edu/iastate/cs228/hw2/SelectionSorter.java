package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm="Selection Sort";

	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int n = points.length;
		for (int i = 0; i<n-1; i++) {
				Point min = points[i];
				int jmin = i;
				 for (int j = i+1; j< n;j++) {
				 if (points[j].compareTo(min)<0) {
				 min = points[j];
				 jmin = j;
				 }
				 }
				 swap(i,jmin);
		}
		
	}	
}
