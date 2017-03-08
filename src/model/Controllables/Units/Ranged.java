package model.Controllables.Units;
import model.Controllables.Stats.RangedStats;
import utilities.Visitor;

public class Ranged extends Unit {

	RangedStats myStats;

	public int getUpkeep(){
		return this.myStats.getUpkeep();
	}

	public int getHealth(){
		return this.myStats.getHealth();
	}

	public int getArmor() {
		return this.myStats.getArmor();
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public Ranged(){
		this.myStats.setHealth(100);
		this.myStats.setArmor(5);
		this.myStats.setUpkeep(5);
		this.myStats.setDefensiveDamage(10);
		this.myStats.setInfluenceRadius(2);
		this.myStats.setOffensiveDamage(10);
		this.myStats.setMovement(2);
		this.setCurrentHealth(100);
	}


}
