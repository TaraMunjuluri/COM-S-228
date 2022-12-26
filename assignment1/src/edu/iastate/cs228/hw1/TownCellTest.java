package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the TownCellTest Class
 * @author Tara Munjuluri
 *
 */
public class TownCellTest {
	/**
	 * Testing the who method
	 */
		@Test
		public void testMyWho() {
			Town t=new Town(4,4);
			TownCell c=new Outage(t, 2, 3);
			assertEquals(c.who(),State.OUTAGE);
					
		}
		/**
		 * Testing the next method
		 */
		@Test
		public void testMyNext() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			Casual c=new Casual(t,1,2);
			assertEquals("Next unit should be outage",State.OUTAGE,c.next(t).who());
			c.next(t).who();
		}
		/**
		 * Testing my census method
		 */
		@Test
		public void testMyCensus() {
			Town t=new Town(4, 4);
			t.randomInit(10);
			TownCell cell=t.grid[0][1];
			cell.census(TownCell.nCensus);
			int[] myCensus= new int[] {
					0,2,1,2,0};
			
			for(int i=0; i<TownCell.nCensus.length;i++) {
				assertEquals(myCensus[i],TownCell.nCensus[i]);
			}
				
		}
		
	}
