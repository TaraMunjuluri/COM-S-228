package edu.iastate.cs228.hw1;
/**
 * @Author Tara Munjuluri
 */
public class Empty extends TownCell {

	public Empty(Town p, int r, int c) {
		// TODO Auto-generated constructor stub
		super(p, r, c);
	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus);
		if(nCensus[EMPTY]+ nCensus[OUTAGE] <=1 ){
			tNew.grid[row][col]=new Reseller(tNew,row,col);
		}	
		else{
			 tNew.grid[row][col]=new Casual(tNew,row,col);	
		}
		return tNew.grid[row][col];
		
	}

}
