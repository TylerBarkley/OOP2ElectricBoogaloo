package model.Controllables.Units;

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

	public void killMe() {
		notifyObservers(id);
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
	
	public void setID(UnitID id)
	{
		this.id=id;
	}

	public UnitID getID() 
	{
		return id;
	}
}
