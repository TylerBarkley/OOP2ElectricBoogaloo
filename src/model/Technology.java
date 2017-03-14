package model;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Units.Unit;
import utilities.TechnologyVisitor;

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
                unitStats.get(unitType).incrementHealth(unitAdvancements.get(unitType).getHealthIncrement());
                unitAdvancements.get(unitType).incrementHealth();
                break;
            case "armor":
                unitStats.get(unitType).incrementArmor(unitAdvancements.get(unitType).getArmorIncrement());
                unitAdvancements.get(unitType).incrementArmor();
                break;
            case "upkeep":
                unitStats.get(unitType).incrementUpkeep(unitAdvancements.get(unitType).getUpKeepDecrement());
                unitAdvancements.get(unitType).decrementUpkeep();
                break;
            case "defensiveDamage":
                unitStats.get(unitType).incrementDefensiveDamage(unitAdvancements.get(unitType).getDefensiveDamageIncrement());
                unitAdvancements.get(unitType).incrementDefensiveDamage();
                break;
            case "influenceRadius":
                unitStats.get(unitType).incrementInfluenceRadius(unitAdvancements.get(unitType).getInfluenceRadiusIncrement());
                unitAdvancements.get(unitType).incrementInfluenceRadius();
                break;
            case "offensiveDamage":
                unitStats.get(unitType).incrementOffensiveDamage(unitAdvancements.get(unitType).getOffensiveDamageIncrement());
                unitAdvancements.get(unitType).incrementOffensiveDamage();
                break;
            case "movement":
                unitStats.get(unitType).incrementMovement(unitAdvancements.get(unitType).getMovementIncrement());
                unitAdvancements.get(unitType).incrementMovement();
                break;
        }

    }

    public void editStructureStats(int structureType,String statToBeModified){
        switch(statToBeModified){
            case "health":
                structureStats.get(structureType).incrementHealth(structureAdvancements.get(structureType).getHealthIncrement());
                structureAdvancements.get(structureType).incrementHealth();
                break;
            case "armor":
                structureStats.get(structureType).incrementArmor(structureAdvancements.get(structureType).getArmorIncrement());
                structureAdvancements.get(structureType).incrementArmor();
                break;
            case "upkeep":
                structureStats.get(structureType).incrementUpkeep(structureAdvancements.get(structureType).getUpKeepDecrement());
                structureAdvancements.get(structureType).decrementUpkeep();
                break;
            case "defensiveDamage":
                structureStats.get(structureType).incrementDefensiveDamage(structureAdvancements.get(structureType).getDefensiveDamageIncrement());
                structureAdvancements.get(structureType).incrementDefensiveDamage();
                break;
            case "influenceRadius":
                structureStats.get(structureType).incrementInfluenceRadius(structureAdvancements.get(structureType).getInfluenceRadiusIncrement());
                structureAdvancements.get(structureType).incrementInfluenceRadius();
                break;
            case "offensiveDamage":
                structureStats.get(structureType).incrementOffensiveDamage(structureAdvancements.get(structureType).getOffensiveDamageIncrement());
                structureAdvancements.get(structureType).incrementOffensiveDamage();
                break;
            case "productionRate":
                structureStats.get(structureType).incrementProductionRate(structureAdvancements.get(structureType).getProductionRateIncrement());
               structureAdvancements.get(structureType).incrementProductionRate();
                break;
            /*case "workerRadius":
                structureStats.get(structureType).incrementWorkerRadius(structureAdvancements.get(structureType).getWorkerRadiusIncrement());
                structureAdvancements.get(structureType).incrementWorkerRadius();
                break;*/

            }
}
public void editWorkerStats(String statToBeModified) {
    switch (statToBeModified) {
        case "foodProduction":
            workerStats.incrementFoodProduction(workerAdvancements.getFoodProductionIncrement());
            workerAdvancements.incrementFoodProduction();
            break;
        case "oreProduction":
            workerStats.incrementOreProduction(workerAdvancements.getOreProductionIncrement());
            workerAdvancements.incrementOreProduction();
            break;
        case "energyProduction":
            workerStats.incrementEnergyProduction(workerAdvancements.getEnergyProductionIncrement());
            workerAdvancements.incrementEnergyProduction();
            break;
        case "technologyProduction":
            workerStats.incrementTechnologyProduction(workerAdvancements.getTechnologyProductionIncrement());
            workerAdvancements.incrementTechnologyProduction();
            break;
        case "soldierTraining":
            workerStats.incrementSoldierTraining(workerAdvancements.getSoldierTrainingIncrement());
            workerAdvancements.incrementSoldierTraining();
            break;
        case "breeding":
            workerStats.incrementBreeding(workerAdvancements.getBreedingIncrement());
            workerAdvancements.incrementBreeding();
            break;
        case "explorerTraining":
            workerStats.incrementExplorerTraining(workerAdvancements.getExplorerTrainingIncrement());
            workerAdvancements.incrementExplorerTraining();
            break;
        case "buildingRate":
            workerStats.incrementBuildingRate(workerAdvancements.getBuildingRateIncrement());
            workerAdvancements.incrementBuildingRate();
            break;
        case "workerRadius":
            workerStats.incrementWorkerRadius(workerAdvancements.getWorkerRadiusIncrement());
            workerAdvancements.incrementBuildingRate();
            break;
        case "workerDensity":
            workerStats.incrementWorkerDensity(workerAdvancements.getWorkerDensityIncrement());
            workerAdvancements.incrementBuildingRate();
            break;
    }
}
    public int getCurrentStructureAdvancements(int structureType, String statToBeSearched){
        switch(statToBeSearched){
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
    public int getCurrentUnitAdvancements(int unitType, String statToBeSearched){
        switch(statToBeSearched){
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
    public int getCurrentWorkerAdvancements(String statToBeSearched) {
        switch (statToBeSearched) {
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
            case "workerRadius":
                return workerAdvancements.getWorkerRadius();
            case "workerDensity":
                return workerAdvancements.getWorkerDensity();
        }
        return -1;
    }
    public int getWorkerStatsIncrements(String statToBeSearched){
        switch (statToBeSearched) {
            case "foodProduction":
                return workerAdvancements.getFoodProductionIncrement();
            case "oreProduction":
                return workerAdvancements.getOreProductionIncrement();
            case "energyProduction":
                return workerAdvancements.getEnergyProductionIncrement();
            case "technologyProduction":
                return workerAdvancements.getTechnologyProductionIncrement();
            case "soldierTraining":
                return workerAdvancements.getSoldierTrainingIncrement();
            case "breeding":
                return workerAdvancements.getBreedingIncrement();
            case "explorerTraining":
                return workerAdvancements.getExplorerTrainingIncrement();
            case "buildingRate":
                return workerAdvancements.getBuildingRateIncrement();
            case "workerRadius":
                return workerAdvancements.getWorkerRadiusIncrement();
            case "workerDensity":
                return workerAdvancements.getWorkerDensityIncrement();
        }
        return -1;
    }
    public int getUnitStatsIncrements(int unitType, String statToBeSearched){
        switch(statToBeSearched){
            case "health":
                return unitAdvancements.get(unitType).getHealthIncrement();
            case "armor":
                return unitAdvancements.get(unitType).getArmorIncrement();
            case "upkeep":
                return unitAdvancements.get(unitType).getUpKeepDecrement();
            case "defensiveDamage":
                return unitAdvancements.get(unitType).getDefensiveDamageIncrement();
            case "influenceRadius":
                return unitAdvancements.get(unitType).getInfluenceRadiusIncrement();
            case "offensiveDamage":
                return unitAdvancements.get(unitType).getOffensiveDamageIncrement();
            case "movement":
                return unitAdvancements.get(unitType).getMovementIncrement();
        }
        return -1;
    }
    public int getStructureStatsIncrements(int structureType, String statToBeSearched){
        //TODO: Implement this shit
        switch(statToBeSearched){
            case "health":
                return structureAdvancements.get(structureType).getHealthIncrement();
            case "armor":
                return structureAdvancements.get(structureType).getArmorIncrement();
            case "upkeep":
                return structureAdvancements.get(structureType).getUpKeepDecrement();
            case "defensiveDamage":
                return structureAdvancements.get(structureType).getDefensiveDamageIncrement();
            case "influenceRadius":
                return structureAdvancements.get(structureType).getInfluenceRadiusIncrement();
            case "offensiveDamage":
                return structureAdvancements.get(structureType).getOffensiveDamageIncrement();
            case "productionRate":
                return structureAdvancements.get(structureType).getProductionRateIncrement();
            case "workerRadius":
                return structureAdvancements.get(structureType).getWorkerRadiusIncrement();
        }
        return -1;
    }
    public int getWorkerStatsMaxLevel(String statToBeSearched){
        //TODO: Implement This Shit
        switch (statToBeSearched) {
            case "foodProduction":
                return workerAdvancements.getMaxFoodProductionLevel();
            case "oreProduction":
                return workerAdvancements.getMaxOreProductionLevel();
            case "energyProduction":
                return workerAdvancements.getMaxEnergyProductionLevel();
            case "technologyProduction":
                return workerAdvancements.getMaxTechnologyProductionLevel();
            case "soldierTraining":
                return workerAdvancements.getMaxSoldierTrainingLevel();
            case "breeding":
                return workerAdvancements.getMaxBreedingLevel();
            case "explorerTraining":
                return workerAdvancements.getMaxBreedingLevel();
            case "buildingRate":
                return workerAdvancements.getMaxBuildingRateLevel();
            case "workerRadius":
                return workerAdvancements.getMaxWorkerRadiusLevel();
            case "workerDensity":
                return workerAdvancements.getMaxWorkerDensityLevel();
        }
        return -1;
    }
    public int getUnitStatsMaxLevel(int unitType, String statToBeSearched){
        switch(statToBeSearched){
            case "health":
                return unitAdvancements.get(unitType).getMaxHealthLevel();
            case "armor":
                return unitAdvancements.get(unitType).getMaxArmorLevel();
            case "upkeep":
                return unitAdvancements.get(unitType).getMaxUpKeepLevel();
            case "defensiveDamage":
                return unitAdvancements.get(unitType).getMaxDefensiveDamageLevel();
            case "influenceRadius":
                return unitAdvancements.get(unitType).getMaxInfluenceRadiusLevel();
            case "offensiveDamage":
                return unitAdvancements.get(unitType).getMaxOffensiveDamageLevel();
            case "movement":
                return unitAdvancements.get(unitType).getMaxMovementLevel();
        }
        return -1;
    }
    public int getStructureStatsMaxLevel(int structureType, String statToBeSearched){
        switch(statToBeSearched){
            case "health":
                return structureAdvancements.get(structureType).getMaxHealthLevel();
            case "armor":
                return structureAdvancements.get(structureType).getMaxArmorLevel();
            case "upkeep":
                return structureAdvancements.get(structureType).getMaxUpKeepLevel();
            case "defensiveDamage":
                return structureAdvancements.get(structureType).getMaxDefensiveDamageLevel();
            case "influenceRadius":
                return structureAdvancements.get(structureType).getMaxInfluenceRadiusLevel();
            case "offensiveDamage":
                return structureAdvancements.get(structureType).getMaxOffensiveDamageLevel();
            case "productionRate":
                return structureAdvancements.get(structureType).getMaxProductionRateLevel();
            case "workerRadius":
                return structureAdvancements.get(structureType).getMaxWorkerRadiusLevel();
        }
        return -1;
    }
    public int getWorkerStatsCurrentLevel(String statToBeSearched){
        //TODO: Implement This Shit

        switch (statToBeSearched) {
            case "foodProduction":
                return workerAdvancements.getFoodProduction()/workerAdvancements.getFoodProductionIncrement();
            case "oreProduction":
                return workerAdvancements.getOreProduction()/workerAdvancements.getOreProductionIncrement();
            case "energyProduction":
                return workerAdvancements.getEnergyProduction()/workerAdvancements.getEnergyProductionIncrement();
            case "technologyProduction":
                return workerAdvancements.getTechnologyProduction()/workerAdvancements.getTechnologyProduction();
            case "soldierTraining":
                return workerAdvancements.getSoldierTraining()/workerAdvancements.getSoldierTrainingIncrement();
            case "breeding":
                return workerAdvancements.getBreeding()/workerAdvancements.getBreedingIncrement();
            case "explorerTraining":
                return workerAdvancements.getExplorerTraining()/workerAdvancements.getExplorerTrainingIncrement();
            case "buildingRate":
                return workerAdvancements.getBuildingRate()/workerAdvancements.getBuildingRateIncrement();
            case "workerRadius":
                return workerAdvancements.getWorkerRadius()/workerAdvancements.getWorkerRadiusIncrement();
            case "workerDensity":
                return workerAdvancements.getWorkerDensity()/workerAdvancements.getWorkerDensityIncrement();
        }
        return -1;
    }
    public int getUnitStatsCurrentLevel(int unitType, String statToBeSearched){
        switch(statToBeSearched){
            case "health":
                return unitAdvancements.get(unitType).getHealth()/unitAdvancements.get(unitType).getHealthIncrement();
            case "armor":
                return unitAdvancements.get(unitType).getArmor()/unitAdvancements.get(unitType).getArmorIncrement();
            case "upkeep":
                return unitAdvancements.get(unitType).getUpkeep()/unitAdvancements.get(unitType).getUpKeepDecrement();
            case "defensiveDamage":
                return unitAdvancements.get(unitType).getDefensiveDamage()/unitAdvancements.get(unitType).getDefensiveDamageIncrement();
            case "influenceRadius":
                return unitAdvancements.get(unitType).getInfluenceRadius()/unitAdvancements.get(unitType).getInfluenceRadiusIncrement();
            case "offensiveDamage":
                return unitAdvancements.get(unitType).getOffensiveDamage()/unitAdvancements.get(unitType).getOffensiveDamageIncrement();
            case "movement":
                return unitAdvancements.get(unitType).getMovement()/unitAdvancements.get(unitType).getMovementIncrement();
        }
        return -1;
    }
    public int getStructureStatsCurrentLevel(int structureType, String statToBeSearched){
        switch(statToBeSearched){
            case "health":
                return structureAdvancements.get(structureType).getHealth()/structureAdvancements.get(structureType).getHealthIncrement();
            case "armor":
                return structureAdvancements.get(structureType).getArmor()/structureAdvancements.get(structureType).getArmorIncrement();
            case "upkeep":
                return structureAdvancements.get(structureType).getUpkeep()/structureAdvancements.get(structureType).getUpKeepDecrement();
            case "defensiveDamage":
                return structureAdvancements.get(structureType).getDefensiveDamage()/structureAdvancements.get(structureType).getDefensiveDamageIncrement();
            case "influenceRadius":
                return structureAdvancements.get(structureType).getInfluenceRadius()/structureAdvancements.get(structureType).getInfluenceRadiusIncrement();
            case "offensiveDamage":
                return structureAdvancements.get(structureType).getOffensiveDamage()/structureAdvancements.get(structureType).getOffensiveDamageIncrement();
            case "productionRate":
                return structureAdvancements.get(structureType).getProductionRate()/structureAdvancements.get(structureType).getProductionRateIncrement();
            case "workerRadius":
                return structureAdvancements.get(structureType).getWorkerRadius()/structureAdvancements.get(structureType).getWorkerRadiusIncrement();
        }
        return -1;
    }
    public void accept(TechnologyVisitor vistor){
        vistor.visit(this);
    }

}
