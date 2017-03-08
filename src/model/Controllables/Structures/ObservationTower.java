package model.Controllables.Structures;

import model.Controllables.Stats.ObservationTowerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class ObservationTower extends Structure {
    //TODO nothing, cause this class is useless
    // HK: ^^^LOLOL

    ObservationTowerStats myStats;

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
