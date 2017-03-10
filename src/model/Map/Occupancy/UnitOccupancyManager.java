package model.Map.Occupancy;

import model.Controllables.Units.Unit;
import model.Location;
import model.Map.Manager;
import model.player.PlayerID;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class UnitOccupancyManager extends Manager<UnitOccupancy> {

    public boolean checkPlayer(PlayerID pid, Location loc){

        if(get(loc) == null){
            return true;
        }

        return pid == get(loc).getPid();
    }

    public void removeUnit(Unit target, Location loc){
        UnitOccupancy uo = get(loc);
        uo.removeUnit(target);

        if(uo.getUnitCount() == 0){
            remove(loc);
        }
    }

    public Unit addUnit(Unit target, Location loc){
        UnitOccupancy uo = get(loc);

        target.setLocation(loc);

        if(uo != null){
            uo.addUnit(target);
            return target;
        }

        uo = new UnitOccupancy();

        uo.addUnit(target);

        this.add(loc, uo);

        return target;
    }

    public void attackLocation(int intensity, Location loc){
        if(this.get(loc) == null){
            return;
        }
        this.get(loc).damageAll(intensity);
    }

}
