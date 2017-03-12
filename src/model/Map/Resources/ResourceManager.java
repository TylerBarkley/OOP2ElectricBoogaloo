package model.Map.Resources;

import model.Location;
import model.Map.Manager;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class ResourceManager extends Manager<ResourceLevel> {

    //Basic resource manager to put in the map
    private boolean working;

    public int mineOre(Location loc, int amount){
        return this.get(loc).mineOreLevel(amount);
    }
    public int mineEnergy(Location loc, int amount){
        return this.get(loc).mineEnergyLevel(amount);
    }
    public int mineFood(Location loc, int amount){
        return this.get(loc).mineFoodLevel(amount);
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
