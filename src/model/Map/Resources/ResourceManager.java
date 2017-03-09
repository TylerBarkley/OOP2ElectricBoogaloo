package model.Map.Resources;

import model.Location;
import model.Map.Manager;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class ResourceManager extends Manager<ResourceLevel> {

    //Basic resource manager to put in the map

    public int mineOre(Location loc, int ammount){
        return this.get(loc).mineOreLevel(ammount);
    }
    public int mineEnegry(Location loc, int ammount){
        return this.get(loc).mineEnergyLevel(ammount);
    }
    public int mineFood(Location loc, int ammount){
        return this.get(loc).mineFoodLevel(ammount);
    }

}
