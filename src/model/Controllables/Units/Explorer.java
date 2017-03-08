package model.Controllables.Units;

import model.Controllables.Stats.ExplorerStats;
import utilities.Visitor;

public class Explorer extends Unit {

	ExplorerStats myStats;

	public int getUpkeep(){
		return this.myStats.getUpkeep();
	}

	public int getHealth(){
		return this.myStats.getHealth();
	}

	public int getArmor(){
		return this.myStats.getArmor();
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
