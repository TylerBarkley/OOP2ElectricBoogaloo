package model.Map.Occupancy;

import model.Controllables.Units.Unit;
import model.Location;
import model.Map.Items.Manager;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class UnitOccupancyManager extends Manager<UnitOccupancy> {

    public boolean checkPlayer(int pid, Location loc){
        return pid == get(loc).getPid();
    }

    public void removeUnit(Unit target, Location loc){
        get(loc).removeUnit(target);
    }

}
