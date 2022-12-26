package edu.iastate.cs228.hw1;
/**
 * @Author Tara Munjuluri
 */
public class Outage extends TownCell {

	public Outage(Town p, int r, int c) {
		// TODO Auto-generated constructor stub
		super(p, r, c);
	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		return tNew.grid[row][col]=new Empty(tNew,row,col);	

		}
	}


