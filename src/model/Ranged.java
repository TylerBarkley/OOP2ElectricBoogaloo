package model;

import utilities.Visitor;

public class Ranged extends Unit{

	public Ranged(){
		//TODO REPLACE WITH STATS FACTORY
		this.setMyStats(new UnitStats(100, 5, 5, 10, 2, 10, 2));
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
