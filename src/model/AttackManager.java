package model;

import model.Controllables.Army;
import model.Map.Map;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancyManager;

/**
 * Created by zrgam_000 on 3/10/2017.
 */
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

        if(!structureOccupancyManager.checkPlayer(attacker.getPlayerID(), loc)){
            structureOccupancyManager.attackLocation(attacker.getAttackDamage(), loc);
        }

        if(!unitOccupancyManager.checkPlayer(attacker.getPlayerID(), loc)) {
            unitOccupancyManager.attackLocation(attacker.getAttackDamage(), loc);
        }
    }
}
