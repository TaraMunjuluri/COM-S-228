package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the EMPTY Class
 * @author taram
 *
 */
public class EmptyTest {
	/**
	 * Testing the who method
	 */
		@Test
		public void testMyWho() {
			Town t=new Town(4,4);
			Empty c=new Empty(t, 2, 3);
			assertEquals(c.who(),State.EMPTY);
					
		}
		/**
		 * Testing my next method
		 */
		@Test
		public void testMyNext() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			Empty c=new Empty(t,0,1);
			assertEquals("Next unit should be casual",State.CASUAL,c.next(t).who());
			c.next(t).who();
		}
		
	}

