package model;

import model.Controllables.Units.Unit;
import model.Map.AOE.AOE;
import model.Map.Items.OneShotItem;
import model.Map.Items.OneShotManager;
import model.Map.Map;
import model.Map.Occupancy.StructureOccupancy;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancy;
import model.Map.Occupancy.UnitOccupancyManager;
import model.Map.Terrain.Mountain;
import model.Map.Terrain.Terrain;
import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;

import java.util.HashMap;

/**
 * Created by zrgam_000 on 3/8/2017.
 */
public class MovementManager {
    private Map map;
    private StructureOccupancyManager structureOccupancyManager;
    private UnitOccupancyManager unitOccupancyManager;
    private OneShotManager oneShotManager;

    private static MovementManager mm;

    private MovementManager()
    {
        this.map = Map.getInstance();
        structureOccupancyManager = map.getStructureOccupancyManager();
        unitOccupancyManager = map.getUnitOccupancyManager();
        oneShotManager = map.getOneShotManager();
    }

    public static MovementManager getInstance()
    {
        if(mm == null)
        {
            mm = new MovementManager();
        }

        return mm;
    }


    MovementManager(Map map){
        this.map = map;
        structureOccupancyManager = map.getStructureOccupancyManager();
        unitOccupancyManager = map.getUnitOccupancyManager();
        oneShotManager = map.getOneShotManager();
    }

    public boolean validateMove(Unit target, Location location){
        if(map.getObstacleAt(location) != null){
            return false;
        }

        if(!structureOccupancyManager.checkPlayer(target.getPid(), location)){
            return false;
        }

        if(!unitOccupancyManager.checkPlayer(target.getPid(), location)){
            return false;
        }

        if(map.getTileAt(location).getTerrain().equals(Mountain.getMountainTerrain())){
            return false;
        }

        return true;
    }

    public boolean validateMove(Unit target, MapDirection md){

        Location location = target.getLocation().getAdjacent(md);

        return validateMove(target, location);
    }

    public boolean validateMove(PlayerID pid, Location location){
        if(map.getObstacleAt(location) != null){
            return false;
        }

        if(!structureOccupancyManager.checkPlayer(pid, location)){
            return false;
        }

        if(!unitOccupancyManager.checkPlayer(pid, location)){
            return false;
        }

        if(map.getTileAt(location).getTerrain().equals(Mountain.getMountainTerrain())){
            return false;
        }

        return true;
    }


    public void makeMove(Unit target, Location location){
        //Precondition:  You have already validated the move

        unitOccupancyManager.removeUnit(target, target.getLocation());

        unitOccupancyManager.addUnit(target, location);

        target.setLocation(location);

        AOE targetAOE = map.getAoEAt(location);

        oneShotManager.useItem(location, target);

        if(targetAOE != null){
            targetAOE.apply(target);
        }

    }

    public void makeMove(Unit target, MapDirection md){
        //Precondition:  You have already validated the move

        Location location = target.getLocation().getAdjacent(md);

        target.setMapDirection(md);

        this.makeMove(target, location);
    }

    public UnitOccupancyManager getUnitOccupancyManager() {
        return unitOccupancyManager;
    }

	public static void reset() {
		mm=null;
	}
}
