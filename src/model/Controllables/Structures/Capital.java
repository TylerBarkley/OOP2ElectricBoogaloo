package model.Controllables.Structures;

import model.Controllables.BasicStats;
import model.Controllables.Stats.CapitalStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Capital extends Structure {

    CapitalStats myStats;

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
