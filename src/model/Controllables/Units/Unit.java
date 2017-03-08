package model.Controllables.Units;

import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.observers.DeathObservable;
import utilities.Visitor;


import model.UnitID;
import model.UnitStats;


public abstract class Unit extends DeathObservable implements Controllable //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private UnitStats myStats;
	private UnitID id;

	public abstract void accept(Visitor visitor);
=======


public abstract class Unit implements Controllable, BasicStats //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	BasicStats myStats;
	
	//public abstract void accept(Visitor visitor);


	public void killMe() {
		notifyObservers(id);
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

	public void setCurrentHealth(int i) {
		this.currentHealth = i;
	}

	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}
	
	public void setID(UnitID id)
	{
		this.id=id;
	}

	public UnitID getID() 
	{
		return id;
	}

	public int getUpkeep(){
		//TODO This may be abstract but it needs to be implemented;
		return 0;
	}

	public void malnourish() {
		// TODO This is called when there aren't enough resources for upkeep	
	}
}
