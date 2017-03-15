package model;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Stats.WorkerStats;

import utilities.TechnologyVisitor;

import java.util.ArrayList;

public class Technology {
private WorkerStats workerStats;
private ArrayList<UnitStats> unitStats;
private ArrayList<StructureStats> structureStats;
private ArrayList<UnitAdvancements> unitAdvancements;
private ArrayList<StructureAdvancements> structureAdvancements;
private WorkerAdvancements workerAdvancements;
private ArrayList<ResearchCommand> researchCommands;

    static final int OffensiveDamage=0;
    static final int DefensiveDamage=1;
    static final int Upkeep=2;
    static final int Movement=3;
    static final int Armor=4;
    static final int InfluenceRadius=6;
    static final  int ProductionRate=7;
    static final int FoodProduction=8;
    static final int OreProduction=9;
    static final int EnergyProduction=10;
    static final int TechnologyProduction=11;
    static final int SoldierTraining=12;
    static final int Breeding=13;
    static final int ExplorerTraining=14;
    static final int BuildingRate=15;
    static final int WorkerDensity=16;
    static final int WorkerRadius=17;
    static final int Health=18;

public Technology(ArrayList<UnitStats> unitStats, ArrayList<StructureStats> structureStats, WorkerStats workerStats){
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

public void editUnitStats(int unitType,int statToBeModified){
        if(getUnitStatCurrentLevel(unitType,statToBeModified)>=getUnitStatsMaxLevel(unitType,statToBeModified)){return;}
        switch(statToBeModified){
            case Health:
                unitStats.get(unitType).incrementHealth(unitAdvancements.get(unitType).getHealthIncrement());
                unitAdvancements.get(unitType).incrementHealth();
                break;
            case Armor:
                unitStats.get(unitType).incrementArmor(unitAdvancements.get(unitType).getArmorIncrement());
                unitAdvancements.get(unitType).incrementArmor();
                break;
            case Upkeep:
                unitStats.get(unitType).incrementUpkeep(unitAdvancements.get(unitType).getUpKeepDecrement());
                unitAdvancements.get(unitType).decrementUpkeep();
                break;
            case DefensiveDamage:
                unitStats.get(unitType).incrementDefensiveDamage(unitAdvancements.get(unitType).getDefensiveDamageIncrement());
                unitAdvancements.get(unitType).incrementDefensiveDamage();
                break;
            case InfluenceRadius:
                unitStats.get(unitType).incrementInfluenceRadius(unitAdvancements.get(unitType).getInfluenceRadiusIncrement());
                unitAdvancements.get(unitType).incrementInfluenceRadius();
                break;
            case OffensiveDamage:
                unitStats.get(unitType).incrementOffensiveDamage(unitAdvancements.get(unitType).getOffensiveDamageIncrement());
                unitAdvancements.get(unitType).incrementOffensiveDamage();
                break;
            case Movement:
                unitStats.get(unitType).incrementMovement(unitAdvancements.get(unitType).getMovementIncrement());
                unitAdvancements.get(unitType).incrementMovement();
                break;
        }

    }

    public void editStructureStats(int structureType,int statToBeModified){
        if(getStructureStatsCurrentLevel(structureType,statToBeModified)>=getStructureStatsMaxLevel(structureType,statToBeModified)){return;}
        switch(statToBeModified){
            case Health:
                structureStats.get(structureType).incrementHealth(structureAdvancements.get(structureType).getHealthIncrement());
                structureAdvancements.get(structureType).incrementHealth();
                break;
            case Armor:
                structureStats.get(structureType).incrementArmor(structureAdvancements.get(structureType).getArmorIncrement());
                structureAdvancements.get(structureType).incrementArmor();
                break;
            case Upkeep:
                structureStats.get(structureType).incrementUpkeep(structureAdvancements.get(structureType).getUpKeepDecrement());
                structureAdvancements.get(structureType).decrementUpkeep();
                break;
            case DefensiveDamage:
                structureStats.get(structureType).incrementDefensiveDamage(structureAdvancements.get(structureType).getDefensiveDamageIncrement());
                structureAdvancements.get(structureType).incrementDefensiveDamage();
                break;
            case InfluenceRadius:
                structureStats.get(structureType).incrementInfluenceRadius(structureAdvancements.get(structureType).getInfluenceRadiusIncrement());
                structureAdvancements.get(structureType).incrementInfluenceRadius();
                break;
            case OffensiveDamage:
                structureStats.get(structureType).incrementOffensiveDamage(structureAdvancements.get(structureType).getOffensiveDamageIncrement());
                structureAdvancements.get(structureType).incrementOffensiveDamage();
                break;
            case ProductionRate:
                structureStats.get(structureType).incrementProductionRate(structureAdvancements.get(structureType).getProductionRateIncrement());
               structureAdvancements.get(structureType).incrementProductionRate();
                break;
            }
}
public void editWorkerStats(int statToBeModified) {
    if(getWorkerStatsCurrentLevel(statToBeModified)>=getWorkerStatsMaxLevel(statToBeModified)){return;}
    System.out.println("Current "+getWorkerStatsCurrentLevel(statToBeModified)+"Max  "+getWorkerStatsMaxLevel(statToBeModified));
    switch (statToBeModified) {
        case FoodProduction:
            workerStats.incrementFoodProduction(workerAdvancements.getFoodProductionIncrement());
            workerAdvancements.incrementFoodProduction();
            break;
        case OreProduction:
            workerStats.incrementOreProduction(workerAdvancements.getOreProductionIncrement());
            workerAdvancements.incrementOreProduction();
            break;
        case EnergyProduction:
            workerStats.incrementEnergyProduction(workerAdvancements.getEnergyProductionIncrement());
            workerAdvancements.incrementEnergyProduction();
            break;
        case TechnologyProduction:
            workerStats.incrementTechnologyProduction(workerAdvancements.getTechnologyProductionIncrement());
            workerAdvancements.incrementTechnologyProduction();
            break;
        case SoldierTraining:
            workerStats.incrementSoldierTraining(workerAdvancements.getSoldierTrainingIncrement());
            workerAdvancements.incrementSoldierTraining();
            break;
        case Breeding:
            workerStats.incrementBreeding(workerAdvancements.getBreedingIncrement());
            workerAdvancements.incrementBreeding();
            break;
        case ExplorerTraining:
            workerStats.incrementExplorerTraining(workerAdvancements.getExplorerTrainingIncrement());
            workerAdvancements.incrementExplorerTraining();
            break;
        case BuildingRate:
            workerStats.incrementBuildingRate(workerAdvancements.getBuildingRateIncrement());
            workerAdvancements.incrementBuildingRate();
            break;
        case WorkerRadius:
            workerStats.incrementWorkerRadius(workerAdvancements.getWorkerRadiusIncrement());
            workerAdvancements.incrementWorkerRadius();
            break;
        case WorkerDensity:
            workerStats.incrementWorkerDensity(workerAdvancements.getWorkerDensityIncrement());
            workerAdvancements.incrementWorkerDensity();
            break;
    }
}
    public int getCurrentStructureAdvancements(int structureType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return structureAdvancements.get(structureType).getHealth();
            case Armor:
                return structureAdvancements.get(structureType).getArmor();
            case Upkeep:
                return structureAdvancements.get(structureType).getUpkeep();
            case DefensiveDamage:
                return structureAdvancements.get(structureType).getDefensiveDamage();
            case InfluenceRadius:
                return structureAdvancements.get(structureType).getInfluenceRadius();
            case OffensiveDamage:
                return structureAdvancements.get(structureType).getOffensiveDamage();
            case ProductionRate:
                return structureAdvancements.get(structureType).getProductionRate();
            case WorkerRadius:
                return structureAdvancements.get(structureType).getWorkerRadius();
        }
        return -1;
    }
    public int getCurrentUnitAdvancements(int unitType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return unitAdvancements.get(unitType).getHealth();
            case Armor:
                return unitAdvancements.get(unitType).getArmor();
            case Upkeep:
                return unitAdvancements.get(unitType).getUpkeep();
            case DefensiveDamage:
                return unitAdvancements.get(unitType).getDefensiveDamage();
            case InfluenceRadius:
                return unitAdvancements.get(unitType).getInfluenceRadius();
            case OffensiveDamage:
                return unitAdvancements.get(unitType).getOffensiveDamage();
            case Movement:
                return unitAdvancements.get(unitType).getMovement();
        }
     return -1;
    }
    public int getCurrentWorkerAdvancements(int statToBeSearched) {
        switch (statToBeSearched) {
            case FoodProduction:
                return workerAdvancements.getFoodProduction();
            case OreProduction:
                return workerAdvancements.getOreProduction();
            case EnergyProduction:
                return workerAdvancements.getEnergyProduction();
            case TechnologyProduction:
                return workerAdvancements.getTechnologyProduction();
            case SoldierTraining:
                return workerAdvancements.getSoldierTraining();
            case Breeding:
                return workerAdvancements.getBreeding();
            case ExplorerTraining:
                return workerAdvancements.getExplorerTraining();
            case BuildingRate:
                return workerAdvancements.getBuildingRate();
            case WorkerRadius:
                return workerAdvancements.getWorkerRadius();
            case WorkerDensity:
                return workerAdvancements.getWorkerDensity();
        }
        return -1;
    }
    public int getWorkerStatsIncrements(int statToBeSearched){
        switch (statToBeSearched) {
            case FoodProduction:
                return workerAdvancements.getFoodProductionIncrement();
            case OreProduction:
                return workerAdvancements.getOreProductionIncrement();
            case EnergyProduction:
                return workerAdvancements.getEnergyProductionIncrement();
            case TechnologyProduction:
                return workerAdvancements.getTechnologyProductionIncrement();
            case SoldierTraining:
                return workerAdvancements.getSoldierTrainingIncrement();
            case Breeding:
                return workerAdvancements.getBreedingIncrement();
            case ExplorerTraining:
                return workerAdvancements.getExplorerTrainingIncrement();
            case BuildingRate:
                return workerAdvancements.getBuildingRateIncrement();
            case WorkerRadius:
                return workerAdvancements.getWorkerRadiusIncrement();
            case WorkerDensity:
                return workerAdvancements.getWorkerDensityIncrement();
        }
        return -1;
    }
    public int getUnitStatsIncrements(int unitType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return unitAdvancements.get(unitType).getHealthIncrement();
            case Armor:
                return unitAdvancements.get(unitType).getArmorIncrement();
            case Upkeep:
                return unitAdvancements.get(unitType).getUpKeepDecrement();
            case DefensiveDamage:
                return unitAdvancements.get(unitType).getDefensiveDamageIncrement();
            case InfluenceRadius:
                return unitAdvancements.get(unitType).getInfluenceRadiusIncrement();
            case OffensiveDamage:
                return unitAdvancements.get(unitType).getOffensiveDamageIncrement();
            case Movement:
                return unitAdvancements.get(unitType).getMovementIncrement();
        }
        return -1;
    }
    public int getStructureStatsIncrements(int structureType, int statToBeSearched){
        //TODO: Implement this shit
        switch(statToBeSearched){
            case Health:
                return structureAdvancements.get(structureType).getHealthIncrement();
            case Armor:
                return structureAdvancements.get(structureType).getArmorIncrement();
            case Upkeep:
                return structureAdvancements.get(structureType).getUpKeepDecrement();
            case DefensiveDamage:
                return structureAdvancements.get(structureType).getDefensiveDamageIncrement();
            case InfluenceRadius:
                return structureAdvancements.get(structureType).getInfluenceRadiusIncrement();
            case OffensiveDamage:
                return structureAdvancements.get(structureType).getOffensiveDamageIncrement();
            case ProductionRate:
                return structureAdvancements.get(structureType).getProductionRateIncrement();
            case WorkerRadius:
                return structureAdvancements.get(structureType).getWorkerRadiusIncrement();
        }
        return -1;
    }
    public int getWorkerStatsMaxLevel(int statToBeSearched){
        //TODO: Implement This Shit
        switch (statToBeSearched) {
            case FoodProduction:
                return workerAdvancements.getMaxFoodProductionLevel()/workerAdvancements.getFoodProductionIncrement()+1;
            case OreProduction:
                return workerAdvancements.getMaxOreProductionLevel()/workerAdvancements.getMaxOreProductionLevel()+1;
            case EnergyProduction:
                return workerAdvancements.getMaxEnergyProductionLevel()/workerAdvancements.getEnergyProductionIncrement()+1;
            case TechnologyProduction:
                return workerAdvancements.getMaxTechnologyProductionLevel()/workerAdvancements.getTechnologyProduction()+1;
            case SoldierTraining:
                return workerAdvancements.getMaxSoldierTrainingLevel()/workerAdvancements.getSoldierTraining()+1;
            case Breeding:
                return workerAdvancements.getMaxBreedingLevel()/workerAdvancements.getBreedingIncrement()+1;
            case ExplorerTraining:
                return workerAdvancements.getMaxExplorerTrainingLevel()/workerAdvancements.getExplorerTrainingIncrement()+1;
            case BuildingRate:
                return workerAdvancements.getMaxBuildingRateLevel()/workerAdvancements.getBuildingRateIncrement()+1;
            case WorkerRadius:
                return workerAdvancements.getMaxWorkerRadiusLevel()/workerAdvancements.getMaxWorkerRadiusLevel()+1;
            case WorkerDensity:
                return workerAdvancements.getMaxWorkerDensityLevel()/workerAdvancements.getWorkerDensityIncrement()+1;
        }
        return -1;
    }
    public int getUnitStatsMaxLevel(int unitType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return unitAdvancements.get(unitType).getMaxHealthLevel()/unitAdvancements.get(unitType).getHealthIncrement()+1;
            case Armor:
                return unitAdvancements.get(unitType).getMaxArmorLevel()/unitAdvancements.get(unitType).getArmorIncrement()+1;
            case Upkeep:
                return unitAdvancements.get(unitType).getMaxUpKeepLevel()/unitAdvancements.get(unitType).getUpKeepDecrement()+1;
            case DefensiveDamage:
                return unitAdvancements.get(unitType).getMaxDefensiveDamageLevel()/unitAdvancements.get(unitType).getDefensiveDamageIncrement()+1;
            case InfluenceRadius:
                return unitAdvancements.get(unitType).getMaxInfluenceRadiusLevel()/unitAdvancements.get(unitType).getInfluenceRadiusIncrement()+1;
            case OffensiveDamage:
                return unitAdvancements.get(unitType).getMaxOffensiveDamageLevel()/unitAdvancements.get(unitType).getOffensiveDamageIncrement()+1;
            case Movement:
                return unitAdvancements.get(unitType).getMaxMovementLevel()/unitAdvancements.get(unitType).getMovementIncrement()+1;
        }
        return -1;
    }
    public int getStructureStatsMaxLevel(int structureType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return structureAdvancements.get(structureType).getMaxHealthLevel()/structureAdvancements.get(structureType).getHealthIncrement()+1;
            case Armor:
                return structureAdvancements.get(structureType).getMaxArmorLevel()/structureAdvancements.get(structureType).getArmorIncrement()+1;
            case Upkeep:
                return structureAdvancements.get(structureType).getMaxUpKeepLevel()/structureAdvancements.get(structureType).getUpKeepDecrement()+1;
            case DefensiveDamage:
                return structureAdvancements.get(structureType).getMaxDefensiveDamageLevel()/structureAdvancements.get(structureType).getDefensiveDamageIncrement()+1;
            case InfluenceRadius:
                return structureAdvancements.get(structureType).getMaxInfluenceRadiusLevel()/structureAdvancements.get(structureType).getMaxInfluenceRadiusLevel()+1;
            case OffensiveDamage:
                return structureAdvancements.get(structureType).getMaxOffensiveDamageLevel()/structureAdvancements.get(structureType).getOffensiveDamageIncrement()+1;
            case ProductionRate:
                return structureAdvancements.get(structureType).getMaxProductionRateLevel()/structureAdvancements.get(structureType).getProductionRateIncrement()+1;
            case WorkerRadius:
                return structureAdvancements.get(structureType).getMaxWorkerRadiusLevel()/structureAdvancements.get(structureType).getWorkerRadiusIncrement()+1;
        }
        return -1;
    }
    public int getWorkerStatsCurrentLevel(int statToBeSearched){
        //TODO: Implement This Shit
        switch (statToBeSearched) {
            case FoodProduction:
                return workerAdvancements.getFoodProduction()/workerAdvancements.getFoodProductionIncrement()+1;
            case OreProduction:
                return workerAdvancements.getOreProduction()/workerAdvancements.getOreProductionIncrement()+1;
            case EnergyProduction:
                return workerAdvancements.getEnergyProduction()/workerAdvancements.getOreProductionIncrement()+1;
            case TechnologyProduction:
                return workerAdvancements.getTechnologyProduction()/workerAdvancements.getTechnologyProductionIncrement()+1;
            case SoldierTraining:
                return workerAdvancements.getSoldierTraining()/workerAdvancements.getSoldierTrainingIncrement()+1;
            case Breeding:
                return workerAdvancements.getBreeding()/workerAdvancements.getBreedingIncrement()+1;
            case ExplorerTraining:
                return workerAdvancements.getExplorerTraining()/workerAdvancements.getExplorerTrainingIncrement()+1;
            case BuildingRate:
                return workerAdvancements.getBuildingRate()/workerAdvancements.getBuildingRateIncrement()+1;
            case WorkerRadius:
                return workerAdvancements.getWorkerRadius()/workerAdvancements.getWorkerRadiusIncrement()+1;
            case WorkerDensity:
                return workerAdvancements.getWorkerDensity()/workerAdvancements.getWorkerDensityIncrement()+1;
        }
        return -1;
    }
    public int getUnitStatCurrentLevel(int unitType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return unitAdvancements.get(unitType).getHealth()/unitAdvancements.get(unitType).getHealthIncrement()+1;
            case Armor:
                return unitAdvancements.get(unitType).getArmor()/unitAdvancements.get(unitType).getArmorIncrement()+1;
            case Upkeep:
                return unitAdvancements.get(unitType).getUpkeep()/unitAdvancements.get(unitType).getUpkeep()+1;
            case DefensiveDamage:
                return unitAdvancements.get(unitType).getDefensiveDamage()/unitAdvancements.get(unitType).getDefensiveDamage()+1;
            case InfluenceRadius:
                return unitAdvancements.get(unitType).getInfluenceRadius()/unitAdvancements.get(unitType).getInfluenceRadiusIncrement()+1;
            case OffensiveDamage:
                return unitAdvancements.get(unitType).getOffensiveDamage()/unitAdvancements.get(unitType).getOffensiveDamageIncrement()+1;
            case Movement:
                return unitAdvancements.get(unitType).getMovement()/unitAdvancements.get(unitType).getMovement()+1;
        }
        return -1;
    }
    public int getStructureStatsCurrentLevel(int structureType, int statToBeSearched){
        switch(statToBeSearched){
            case Health:
                return structureAdvancements.get(structureType).getHealth()/structureAdvancements.get(structureType).getHealthIncrement()+1;
            case Armor:
                return structureAdvancements.get(structureType).getArmor()/structureAdvancements.get(structureType).getArmorIncrement()+1;
            case Upkeep:
                return structureAdvancements.get(structureType).getUpkeep()/structureAdvancements.get(structureType).getUpkeep()+1;
            case DefensiveDamage:
                return structureAdvancements.get(structureType).getDefensiveDamage()/structureAdvancements.get(structureType).getDefensiveDamage()+1;
            case InfluenceRadius:
                return structureAdvancements.get(structureType).getInfluenceRadius()/structureAdvancements.get(structureType).getInfluenceRadiusIncrement()+1;
            case OffensiveDamage:
                return structureAdvancements.get(structureType).getOffensiveDamage()/structureAdvancements.get(structureType).getOffensiveDamageIncrement()+1;
            case ProductionRate:
                return structureAdvancements.get(structureType).getProductionRate()/structureAdvancements.get(structureType).getProductionRate()+1;
        }
        return -1;
    }
    public void accept(TechnologyVisitor vistor){
        vistor.visit(this);
    }

}
