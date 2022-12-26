package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Tara Munjuluri
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm="Merge Sort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(this.points);
		
		
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		
		
		Point[] leftS=new Point[pts.length/2];
		Point[] rightS=new Point[pts.length-leftS.length];
		Point[] A=new Point[pts.length];
		
		if(pts.length<=1) {
			return;
		}
		
		for(int i=0;i<pts.length/2;i++) {
			leftS[i]=pts[i];
		}
		int i=0;
		for(int j=pts.length/2;j<pts.length;j++) {
			rightS[i]=pts[j];
			i++;
		}
		mergeSortRec(leftS);
		mergeSortRec(rightS);
		
			
		
	    A=merge(leftS,rightS);
		for(int n=0;n<A.length;n++) {
			pts[n]=A[n];
		}
	}
	
	/**
	 * Merges two point arrays that are being passed in
	 * 
	 * @param Point B
	 * @param Point C
	 */
private Point[] merge(Point[] B,Point[] C) {
	int p=B.length;
	int q=C.length;
	Point[] D=new Point[p+q];
	int i=0;
	int j=0;
	
	int counter=0;
	

	while(i<p && j<q) {
		if(B[i].compareTo(C[j])<=0) {
			D[counter]=B[i];
			i++;
			counter++;
		}
		else {
			D[counter]=C[j];
			j++;
			counter++;
		}
	}
		if(i>=p) {
			for(int a=j;a<q;a++) {
				D[counter]=C[a];
				counter++;
			}
		}
		else {
			for(int b=i;b<p;b++) {
				D[counter]=B[i];
				counter++;
			}
		}
			return D;
		}
}
