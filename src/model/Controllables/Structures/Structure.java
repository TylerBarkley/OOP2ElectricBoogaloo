package model.Controllables.Structures;
import model.StructureID;
import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.observers.DeathObservable;
import utilities.Visitor;

public abstract class Structure extends DeathObservable implements Controllable, BasicStats {
    int currentHealth;
    BasicStats myStats;	private StructureID id;

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

    public void makeArmy(){
        //TODO just copy Iteration 1 code for this
    }}
