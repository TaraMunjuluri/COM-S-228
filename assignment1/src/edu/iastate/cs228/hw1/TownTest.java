package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the TownTest Class
 * @author Tara Munjuluri
 *
 */
public class TownTest {
	/**
	 * Testing the Random method
	 */
		@Test
		public void testMyRandom() {
			Town t=new Town(4,4);
			t.randomInit(10);
			assertEquals("The next entry",t.grid[0][3].who(),State.RESELLER);
		}
		
		/**
		 * Testing the length
		 */
		@Test
		public void testMylength() {
			Town t=new Town(4, 2);
			assertEquals("The length should be 4",t.getLength(),4);
		}
		/**
		 * Testing the width
		 */
		@Test
		public void testMyWidth() {
			Town t=new Town(3, 2);
			assertEquals("The length should be 3",t.getWidth(),2);
		}
		/**
		 * Testing To String
		 */
		@Test
		public void testMyToString() {
			Town t=new Town(4,4);
			t.randomInit(10);
			Scanner scnr= new Scanner(t.toString());
			scnr.next();
			assertEquals("R",scnr.next(),"R");
			
		}
		
		
	}
