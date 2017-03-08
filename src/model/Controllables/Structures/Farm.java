package model.Controllables.Structures;

import model.Controllables.Stats.FarmStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends Structure {

    FarmStats myStats;

    public int getUpkeep(){
        return this.myStats.getUpkeep();
    }

    public int getHealth(){
        return this.myStats.getHealth();
    }

    public int getArmor(){
        return this.myStats.getArmor();
    }
}
