package model;

import utilities.Visitor;

public abstract class Unit //implements OverviewVisitable, TurnObserver
{

	UnitStats myStats;

	public MapDirection getFacingDirection(){
		//TODO
		//If you rather return it as an int, that is fine with me (Joshua) too. It is an easy change on my end.
		return MapDirection.getSouthWest();
	}
	
	public abstract void accept(Visitor visitor);

	public void killMe() {
		//TODO KILLING SELF
	}

	public void damageMe(int intensity) {
		//TODO HURT
	}

	public void healMe(int intensity){
		//TODO HEALING
	}
}
