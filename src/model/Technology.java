package model;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Units.Unit;
import java.util.ArrayList;

/**
 * Created by Trevor on 3/8/2017.
 */
public class Technology {
ArrayList<UnitStats> unitStats;
ArrayList<StructureStats> structureStats;
ArrayList<UnitAdvancements> unitAdvancements;
ArrayList<StructureAdvancements> structureAdvancements;
Technology(ArrayList<UnitStats> unitStats, ArrayList<StructureStats> structureStats){
    this.unitStats=unitStats;
    this.structureStats=structureStats;
    unitAdvancements=new ArrayList<UnitAdvancements>(unitStats.size());
    structureAdvancements=new ArrayList<StructureAdvancements>(structureStats.size());
}
public void editUnitStats(int unitType,String statToBeModified){
        switch(statToBeModified){
            case "health":
                unitStats.get(unitType).setHealth(unitStats.get(unitType).getHealth()+1);
                unitAdvancements.get(unitType).setHealth(unitAdvancements.get(unitType).getHealth()+1);
                break;
            case "armor":
                unitStats.get(unitType).setArmor(unitStats.get(unitType).getArmor()+1);
                unitAdvancements.get(unitType).setArmor(unitAdvancements.get(unitType).getArmor()+1);
                break;
            case "upkeep":
                unitStats.get(unitType).setUpkeep(unitStats.get(unitType).getUpkeep()- 1);
                unitAdvancements.get(unitType).setUpkeep(unitAdvancements.get(unitType).getUpkeep()-1);
                break;
            case "defensiveDamage":
                unitStats.get(unitType).setDefensiveDamage(unitStats.get(unitType).getDefensiveDamage()+1);
                unitAdvancements.get(unitType).setDefensiveDamage(unitAdvancements.get(unitType).getDefensiveDamage()+1);
                break;
            case "influenceRadius":
                unitStats.get(unitType).setInfluenceRadius(unitStats.get(unitType).getInfluenceRadius()+1);
                unitAdvancements.get(unitType).setInfluenceRadius(unitAdvancements.get(unitType).getInfluenceRadius()+1);
                break;
            case "offensiveDamage":
                unitStats.get(unitType).setOffensiveDamage(unitStats.get(unitType).getOffensiveDamage()+1);
                unitAdvancements.get(unitType).setOffensiveDamage(unitAdvancements.get(unitType).getOffensiveDamage()+1);
                break;
            case "movement":
                unitStats.get(unitType).setMovement(unitStats.get(unitType).getMovement()+1);
                unitAdvancements.get(unitType).setMovement(unitAdvancements.get(unitType).getMovement()+1);
                break;
        }

    }

    public void editStructureStats(int structureType,String statToBeModified){
        switch(statToBeModified){
            case "health":
                structureStats.get(structureType).setHealth(structureStats.get(structureType).getHealth()+1);
                structureAdvancements.get(structureType).setHealth(structureAdvancements.get(structureType).getHealth()+1);
                break;
            case "armor":
                structureStats.get(structureType).setArmor(structureStats.get(structureType).getArmor()+1);
                structureAdvancements.get(structureType).setArmor(structureAdvancements.get(structureType).getArmor()+1);
                break;
            case "upkeep":
                structureStats.get(structureType).setUpkeep(structureStats.get(structureType).getUpkeep()- 1);
                structureAdvancements.get(structureType).setUpkeep(structureAdvancements.get(structureType).getUpkeep()-1);
                break;
            case "defensiveDamage":
                structureStats.get(structureType).setDefensiveDamage(structureStats.get(structureType).getDefensiveDamage()+1);
                structureAdvancements.get(structureType).setDefensiveDamage(structureAdvancements.get(structureType).getDefensiveDamage()+1);
                break;
            case "influenceRadius":
                structureStats.get(structureType).setInfluenceRadius(structureStats.get(structureType).getInfluenceRadius()+1);
                structureAdvancements.get(structureType).setInfluenceRadius(structureAdvancements.get(structureType).getInfluenceRadius()+1);
                break;
            case "offensiveDamage":
                structureStats.get(structureType).setOffensiveDamage(structureStats.get(structureType).getOffensiveDamage()+1);
                structureAdvancements.get(structureType).setOffensiveDamage(structureAdvancements.get(structureType).getOffensiveDamage()+1);
                break;
            case "productionRate":
                structureStats.get(structureType).setProductionRate(structureStats.get(structureType).getProductionRate()+1);
                structureAdvancements.get(structureType).setProductionRate(structureAdvancements.get(structureType).getProductionRate()+1);
                break;

            }
}
    public int getStructureAdvancements(int structureType, String statToBeModified){
        switch(statToBeModified){
            case "health":
                return structureAdvancements.get(structureType).getHealth();
            case "armor":
                return structureAdvancements.get(structureType).getArmor();
            case "upkeep":
                return structureAdvancements.get(structureType).getUpkeep();
            case "defensiveDamage":
                return structureAdvancements.get(structureType).getDefensiveDamage();
            case "influenceRadius":
                return structureAdvancements.get(structureType).getInfluenceRadius();
            case "offensiveDamage":
                return structureAdvancements.get(structureType).getOffensiveDamage();
            case "productionRate":
                return structureAdvancements.get(structureType).getProductionRate();
        }
        return -1;
    }
    public int getUnitAdvancements(int unitType, String statToBeModified){
        switch(statToBeModified){
            case "health":
                return unitAdvancements.get(unitType).getHealth();
            case "armor":
                return unitAdvancements.get(unitType).getArmor();
            case "upkeep":
                return unitAdvancements.get(unitType).getUpkeep();
            case "defensiveDamage":
                return unitAdvancements.get(unitType).getDefensiveDamage();
            case "influenceRadius":
                return unitAdvancements.get(unitType).getInfluenceRadius();
            case "offensiveDamage":
                return unitAdvancements.get(unitType).getOffensiveDamage();
            case "movement":
                return unitAdvancements.get(unitType).getMovement();
        }
     return -1;
    }
}
