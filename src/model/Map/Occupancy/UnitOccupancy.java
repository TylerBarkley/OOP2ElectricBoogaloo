package model.Map.Occupancy;

import model.Controllables.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class UnitOccupancy {
    ArrayList<Unit> occupyingUnits;
    int pid;

    public void removeUnit(Unit target){
        occupyingUnits.remove(target);
    }

    public void addUnit(Unit added){
        occupyingUnits.add(added);
    }

    public int getPid() {
        return pid;
    }
}
