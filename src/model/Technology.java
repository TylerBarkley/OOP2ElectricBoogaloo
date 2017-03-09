package model;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Units.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Trevor on 3/8/2017.
 */
public class Technology {
WorkerStats workerStats;
ArrayList<UnitStats> unitStats;
ArrayList<StructureStats> structureStats;
ArrayList<UnitAdvancements> unitAdvancements;
ArrayList<StructureAdvancements> structureAdvancements;
WorkerAdvancements workerAdvancements;

Technology(ArrayList<UnitStats> unitStats, ArrayList<StructureStats> structureStats, WorkerStats workerStats){
    this.unitStats=unitStats;
    this.structureStats=structureStats;
    this.workerStats=workerStats;
    unitAdvancements=new ArrayList<UnitAdvancements>();
    structureAdvancements=new ArrayList<StructureAdvancements>();
    for(int i=0;i<unitStats.size();i++){
        unitAdvancements.add(new UnitAdvancements());
    }
    for(int i=0;i<structureStats.size();i++){
        structureAdvancements.add(new StructureAdvancements());
    }
    workerAdvancements=new WorkerAdvancements();

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
            case "workerRadius":
                structureStats.get(structureType).setWorkerRadius(structureStats.get(structureType).getWorkerRadius()+1);
                structureAdvancements.get(structureType).setWorkerRadius(structureAdvancements.get(structureType).getWorkerRadius()+1);
                break;

            }
}
public void editWorkerStats(String statToBeModified) {
    switch (statToBeModified) {
        case "foodProduction":
            workerStats.setFoodProduction(workerStats.getFoodProduction()+1);
            workerAdvancements.setFoodProduction(workerAdvancements.getFoodProduction()+1);
            break;
        case "oreProduction":
            workerStats.setOreProduction(workerStats.getOreProduction()+1);
            workerAdvancements.setOreProduction(workerAdvancements.getOreProduction()+1);
            break;
        case "energyProduction":
            workerStats.setEnergyProduction(workerStats.getEnergyProduction()+1);
            workerAdvancements.setEnergyProduction(workerAdvancements.getEnergyProduction()+1);
            break;
        case "technologyProduction":
            workerStats.setTechnologyProduction(workerStats.getTechnologyProduction()+1);
            workerAdvancements.setTechnologyProduction(workerAdvancements.getTechnologyProduction()+1);
            break;
        case "soldierTraining":
            workerStats.setSoldierTraining(workerStats.getSoldierTraining()+1);
            workerAdvancements.setSoldierTraining(workerAdvancements.getSoldierTraining()+1);
            break;
        case "breeding":
            workerStats.setBreeding(workerStats.getBreeding()+1);
            workerAdvancements.setBreeding(workerAdvancements.getBreeding()+1);
            break;
        case "explorerTraining":
            workerStats.setExplorerTraining(workerStats.getExplorerTraining()+1);
            workerAdvancements.setExplorerTraining(workerAdvancements.getExplorerTraining()+1);
            break;
        case "buildingRate":
            workerStats.setBuildingRate(workerStats.getBuildingRate()+1);
            workerAdvancements.setBuildingRate(workerAdvancements.getBuildingRate()+1);
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
            case "workerRadius":
                return structureAdvancements.get(structureType).getWorkerRadius();
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
    public int getWorkerAdvancements(String statToBeModified){
        switch(statToBeModified){
            case "foodProduction":
                return workerAdvancements.getFoodProduction();
            case "oreProduction":
                return workerAdvancements.getOreProduction();
            case "energyProduction":
                return workerAdvancements.getEnergyProduction();
            case "technologyProduction":
                return workerAdvancements.getTechnologyProduction();
            case "soldierTraining":
                return workerAdvancements.getSoldierTraining();
            case "breeding":
                return workerAdvancements.getBreeding();
            case "explorerTraining":
                return workerAdvancements.getExplorerTraining();
            case "buildingRate":
                return workerAdvancements.getBuildingRate();
        }
        return -1;

    }
}
