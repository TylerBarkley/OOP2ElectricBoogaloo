package model.Controllables.Units;

import model.Controllables.Controllable;
import utilities.Visitor;
import model.UnitStats;


public abstract class Unit implements Controllable //implements OverviewVisitable, TurnObserver
{
	int currentHealth;
	UnitStats myStats;
	
	public abstract void accept(Visitor visitor);

	public void killMe() {
		//TODO KILLING SELF
		//REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
		//POSSIBLY USING PLAYER MANAGER
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

	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getMovement() {
		return myStats.getMovement();
	}
	
	public int getUpkeep() {
		return myStats.getUpkeep();
	}
	
	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}
}
