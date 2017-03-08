package model.Controllables.Units;

import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import utilities.Visitor;


public abstract class Unit implements Controllable, BasicStats //implements OverviewVisitable, TurnObserver
{
	int currentHealth;
	BasicStats myStats;
	
	//public abstract void accept(Visitor visitor);

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

	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}
}
