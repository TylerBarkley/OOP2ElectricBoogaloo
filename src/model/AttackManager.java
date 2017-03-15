package model;

import model.Map.Map;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancyManager;

import java.util.ArrayList;

public class AttackManager {

    private static AttackManager am;
    UnitOccupancyManager unitOccupancyManager;
    StructureOccupancyManager structureOccupancyManager;

    public static AttackManager getInstance() {

        if(am == null){
            am = new AttackManager();
        }
        return am;
    }

    private AttackManager(){
        unitOccupancyManager = Map.getInstance().getUnitOccupancyManager();
        structureOccupancyManager = Map.getInstance().getStructureOccupancyManager();
    }

    public static void reset(){
        am = null;
    }


    public void attack(Attacker attacker, Location loc) {
        ArrayList<Location> locations = loc.getAllLocationsWithinRadius(attacker.getAttackRange());
        //System.out.println("Attack range is " + attacker.getAttackRange());
        //System.out.println("target within location? " + locations.contains(loc));
        if(!locations.contains(attacker.getLocation())){
            return;
        }
        if(!structureOccupancyManager.checkPlayer(attacker.getPlayerID(), loc)){
            structureOccupancyManager.attackLocation(attacker.getAttackDamage(), loc);
        }

        if(!unitOccupancyManager.checkPlayer(attacker.getPlayerID(), loc)) {
            unitOccupancyManager.attackLocation(attacker.getAttackDamage(), loc);
        }
    }
}
