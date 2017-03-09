package model.Controllables.Structures;

import java.util.ArrayList;

import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.Controllables.ControllableID;
import model.Controllables.Stats.StructureStats;
import model.observers.Observable;
import model.player.PlayerID;
import utilities.StructureVisitor;

public abstract class Structure extends Observable<Structure> implements Controllable{
    int currentHealth;
    private StructureStats myStats;
    private StructureID id;
    private boolean isAlive;
    
    
    public Structure()
    {
		//TODO
    	isAlive=true;
	}
    //public abstract void accept(Visitor visitor);

    public void killMe()
    {
    	isAlive=false;
        notifyObservers();
        //TODO KILLING SELF
        //REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
        //POSSIBLY USING PLAYER MANAGER

	}	
	public void setID(StructureID id)
	{
		notifyObservers();
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
        
        notifyObservers();
    }

    public void healMe(int intensity){
        currentHealth += intensity;
        if(currentHealth > myStats.getHealth()){
            currentHealth = myStats.getHealth();
        }
        
        notifyObservers();
    }

    public void setMyStats(StructureStats myStats) {
        this.myStats = myStats;
        
        notifyObservers();
    }

    public StructureStats getMyStats(){
        return this.myStats;
    }


    public PlayerID getPid(){
        return id.getPlayerID();
    }

	public boolean isAlive() {
		return isAlive;
	}
}
