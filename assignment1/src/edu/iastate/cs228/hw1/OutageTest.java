package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**Testing the Outage Class
 * 
 * @author taram
 *
 */
public class OutageTest {
	/**
	 * Testing the who method
	 */
		@Test
		public void testMyWho() {
			Town t=new Town(4,4);
			Outage o=new Outage(t, 2, 3);
			assertEquals(o.who(),State.OUTAGE);
					
		}
	/**
	 * Testing my next method
	 */
		@Test
		public void testMyNext() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			Outage o=new Outage(t,0,2);
			assertEquals("Next unit should be empty",State.EMPTY,o.next(t).who());
			o.next(t).who();
		}
		
	}

