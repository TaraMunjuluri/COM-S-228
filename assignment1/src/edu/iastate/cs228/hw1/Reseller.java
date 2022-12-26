package edu.iastate.cs228.hw1;
/**
 * @Author Tara Munjuluri
 */
public class Reseller extends TownCell {

	public Reseller(Town p, int r, int c) {
		// TODO Auto-generated constructor stub
		super(p, r, c);
	}



	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus);
		
		if(nCensus[CASUAL]<=3) {
			tNew.grid[row][col]=new Empty(tNew,row,col);
		}
		else if(nCensus[EMPTY]>=3) {
			tNew.grid[row][col]=new Empty(tNew,row,col);
			}
		else if(nCensus[CASUAL]>=5) {
			tNew.grid[row][col]=new Streamer(tNew,row,col);
		}
		else {
			tNew.grid[row][col]=new Reseller(tNew,row,col);	
			}
			return tNew.grid[row][col];

		}



	}


