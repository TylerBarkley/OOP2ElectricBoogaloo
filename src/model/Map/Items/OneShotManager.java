package model.Map.Items;

import model.Location;
import model.Controllables.Units.Unit;
import model.Map.Manager;

public class OneShotManager extends Manager<OneShotItem> {

    public void useItem(Location loc, Unit target){

        if(this.get(loc) == null){
            return;
        }

        this.get(loc).applyItem(target);
        this.remove(loc);
    }

}
