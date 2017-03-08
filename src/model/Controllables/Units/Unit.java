package model.Controllables.Units;

import model.Controllables.Controllable;
import utilities.Visitor;
import model.UnitStats;

public abstract class Unit implements Controllable //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private UnitStats myStats;
	
	public abstract void accept(Visitor visitor);

	public void killMe() {
		//TODO KILLING SELF
		//REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
		//POSSIBLY USING PLAYER MANAGER
		//Possibly a death observer/visitor

		//FOR TESTING PURPOSES
		System.out.println("KILLING");
	}

	public void damageMe(int intensity) {
		currentHealth -= (intensity - myStats.getArmor());
		if(currentHealth <= 0){
			this.killMe();
		}
	}

	public void healMe(int intensity){
		currentHealth += intensity;
		if(currentHealth > myStats.getHealth()){
			currentHealth = myStats.getHealth();
		}
	}

	public int getCurrentHealth(){
		return currentHealth;
	}

	public void setMyStats(UnitStats myStats) {
		this.myStats = myStats;
	}

	public void setCurrentHealth(int i) {
		this.currentHealth = i;
	}

	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}
}