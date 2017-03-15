package model.Map.Resources;

import model.Location;
import model.Map.Manager;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class ResourceManager extends Manager<ResourceLevel> {

    //Basic resource manager to put in the map

    public int mineOre(Location loc, int amount){
        System.out.println(loc.toString());
        return this.get(loc).mineOreLevel(amount);
    }
    public int mineEnergy(Location loc, int amount){
        return this.get(loc).mineEnergyLevel(amount);
    }
    public int mineFood(Location loc, int amount){
        return this.get(loc).mineFoodLevel(amount);
    }

    public boolean isWorking(Location loc) {
        return this.get(loc).getWorking();
    }

    public void setWorking(Location loc, boolean working) {
        System.out.println(this.toString());
        System.out.println(this.get(new Location(0,0)));
        System.out.println(this.get(loc));
        this.get(loc).setWorking(working);
    }
}
