package model.Controllables.Units;

import model.Controllables.Stats.MeleeStats;
import utilities.Visitor;

public class Melee extends Unit {

	MeleeStats myStats;

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

	public Melee(){};
}
