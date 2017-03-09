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
import model.player.PlayerID;

/**
 * Created by zrgam_000 on 3/8/2017.
 */
public class MovementManager {
    Map map;
    StructureOccupancyManager structureOccupancyManager;
    UnitOccupancyManager unitOccupancyManager;
    OneShotManager oneShotManager;

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

        if(!map.getStructureOccupancyManager().checkPlayer(target.getPid(), location)){
            return false;
        }

        if(!map.getUnitOccupancyManager().checkPlayer(target.getPid(), location)){
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

        if(!map.getStructureOccupancyManager().checkPlayer(pid, location)){
            return false;
        }

        if(!map.getUnitOccupancyManager().checkPlayer(pid, location)){
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
}
