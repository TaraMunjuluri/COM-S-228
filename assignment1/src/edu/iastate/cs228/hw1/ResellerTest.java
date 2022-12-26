package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the RESELLER Class
 * @author Tara Munjuluri
 *
 */
public class ResellerTest {
	 /**
	  * Testing the who method
	  */
		@Test
		public void testMyWho() {
			Town t=new Town(4,4);
			Reseller c=new Reseller(t, 2, 3);
			assertEquals(c.who(),State.RESELLER);
					
		}
		/**
		 * Testing my next method
		 */
		@Test
		public void testMyNext() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			Reseller c=new Reseller(t,0,1);
			assertEquals("Next unit should be empty",State.EMPTY,c.next(t).who());
			c.next(t).who();
		}
		
	}

