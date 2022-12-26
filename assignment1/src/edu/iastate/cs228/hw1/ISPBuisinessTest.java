package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testing the IspBusiness Class
 * @author Tara Munjuluri
 *
 */
public class ISPBuisinessTest {
	/**
	 * Testing the profit/update method together to see if updated and outputs profit from the correct update
	 */
		@Test
		public void MyProfitUpdate() {
			int p=1;
			Town t=new Town(4,4);
			t.randomInit(10);
			ISPBusiness.updatePlain(t);
			Town newTown=ISPBusiness.updatePlain(t);
			assertEquals(p,ISPBusiness.getProfit(t));		
		}
		
		
		
	}
