package model.Controllables.Structures;
import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.Controllables.Stats.StructureStats;
import utilities.StructureVisitor;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public abstract class Structure implements Controllable {
    int currentHealth;
    private StructureStats myStats;

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

    public void setMyStats(StructureStats myStats) {
        this.myStats = myStats;
    }
    public StructureStats getMyStats(){
        return this.myStats;
    }
}
