package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the Casual Class
 * @author Tara Munjuluri
 */

public class CasualTest {
	/**
	 * Testing the who method
	 */
		@Test
		public void testMyWho() {
			Town t=new Town(4,4);
			Casual c=new Casual(t, 2, 3);
			assertEquals(c.who(),State.CASUAL);
					
		}
		/**
		 * Testing my next method
		 */
		@Test
		public void testMyNext() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			Casual c=new Casual(t,1,2);
			assertEquals("Next unit should be outage",State.OUTAGE,c.next(t).who());
			c.next(t).who();
		}
		
	}



