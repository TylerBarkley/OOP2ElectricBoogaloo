package model.Controllables.Units;
import utilities.Visitor;

public class Ranged extends Unit {


	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public Ranged(){
		/*
		this.getMyStats().setHealth(100);
		this.getMyStats().setArmor(5);
		this.getMyStats().setUpkeep(5);
		this.getMyStats().setDefensiveDamage(10);
		this.getMyStats().setInfluenceRadius(2);
		this.getMyStats().setOffensiveDamage(10);
		this.getMyStats().setMovement(2);
		this.setCurrentHealth(100);
		*/
	}


}
