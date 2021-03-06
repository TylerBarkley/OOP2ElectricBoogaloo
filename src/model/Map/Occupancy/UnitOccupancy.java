package model.Map.Occupancy;

import model.Controllables.Units.Unit;
import model.player.PlayerID;

import java.util.ArrayList;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class UnitOccupancy {
    private ArrayList<Unit> occupyingUnits;
    private PlayerID pid;

    public UnitOccupancy(){
        occupyingUnits = new ArrayList<Unit>();
    }

    public void removeUnit(Unit target){
        occupyingUnits.remove(target);
    }

    public void addUnit(Unit added){
        occupyingUnits.add(added);
        pid = added.getPlayerID();
    }

    public PlayerID getPid() {
        return pid;
    }

    public int getUnitCount(){
        return occupyingUnits.size();
    }

	public ArrayList<Unit> getOccupants() {
		return occupyingUnits;
	}

	public void damageAll(int intensity){
        ArrayList<Unit> tempUnits = new ArrayList<Unit>();
        for(Unit unit : occupyingUnits){
            tempUnits.add(unit);
        }

        for(Unit unit : tempUnits){
            unit.damageMe(intensity);
        }
    }
}
