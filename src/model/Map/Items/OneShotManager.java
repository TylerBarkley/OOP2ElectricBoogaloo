package model.Map.Items;

import model.Location;
import model.Unit;

public class OneShotManager extends Manager<OneShotItem> {

    public void useItem(Location loc, Unit target){
        this.get(loc).applyItem(target);
        this.remove(loc);
    }

}
