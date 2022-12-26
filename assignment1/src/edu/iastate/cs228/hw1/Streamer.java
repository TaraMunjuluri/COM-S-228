package edu.iastate.cs228.hw1;
/*
 * @Author Tara Munjuluri
 */
public class Streamer extends TownCell{

	public Streamer(Town p, int r, int c) {
		// TODO Auto-generated constructor stub
		super(p, r, c);
	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.STREAMER;
	}

	@Override
	public TownCell next(Town tNew) {
		// TODO Auto-generated method stub
		census(this.nCensus);
		if(nCensus[EMPTY]+ nCensus[OUTAGE] <=1 ){
			tNew.grid[row][col]=new Reseller(tNew,row,col);
		}	
		else if(nCensus[RESELLER]>0) {
			tNew.grid[row][col]=new Outage(tNew,row,col);
		}
		else if(nCensus[OUTAGE]>0) {
			tNew.grid[row][col]=new Empty(tNew,row,col);

		}
		else if(nCensus[CASUAL]>=5) {
			tNew.grid[row][col]=new Streamer(tNew,row,col);
		}
		else {
			tNew.grid[row][col]=new Streamer(tNew,row,col);	
			}
			return tNew.grid[row][col];

	}

}
