package model.Controllables.Structures;

import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.Controllables.Stats.StructureStats;

import utilities.StructureVisitor;
import model.observers.DeathObservable;


public abstract class Structure extends DeathObservable implements Controllable {
    int currentHealth;
    private StructureStats myStats;
    private StructureID id;

    //public abstract void accept(Visitor visitor);

    public void killMe()
    {
        notifyObservers(id);
        //TODO KILLING SELF
        //REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
        //POSSIBLY USING PLAYER MANAGER

	}	
	public void setID(StructureID id)
	{
		this.id=id;
	}

	public StructureID getID() 
	{
		return id;
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

    public void setMyStats(StructureStats myStats) {
        this.myStats = myStats;
    }

    public StructureStats getMyStats(){
        return this.myStats;
    }


    //FOR TESTING DO NOT KEEP
    public int getPid(){
        return 100;
    }
}
