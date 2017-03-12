package model.Map.Occupancy;

import model.Controllables.Units.Unit;
import model.Location;
import model.Map.Manager;
import model.observers.UnitObserver;
import model.player.PlayerID;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class UnitOccupancyManager extends Manager<UnitOccupancy> implements UnitObserver{

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

        if(target.getLocation() == null){
            target.addObserver(this);
        }

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

    @Override
    public void update(Unit unit) {
        if(!unit.isAlive()){
            this.removeUnit(unit, unit.getLocation());
        }
    }
}
