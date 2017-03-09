package model;

/**
 * Created by Trevor on 3/8/2017.
 */
public class WorkerAdvancements {
    final private int foodProductionIncrement=1;
    final private int oreProductionIncrement=4;
    final private int energyProductionIncrement=3;
    final private int technologyProductionIncrement=4;
    final private int soldierTrainingIncrement=3;
    final private int breedingIncrement=2;
    final private int explorerTrainingIncrement=1;
    final private int buildingRateIncrement=2;
    final private int maxFoodProductionLevel=2;
    final private int maxOreProductionLevel=2;
    final private int maxEnergyProductionLevel=3;
    final private int maxTechnologyProductionLevel=3;
    final private int maxSoldierTrainingLevel=2;
    final private int maxBreedingLevel=3;
    final private int maxExplorerTrainingLevel=1;
    final private int maxBuildingRateLevel=3;


    private int foodProduction;
    private int oreProduction;
    private int energyProduction;
    private int technologyProduction;
    private int soldierTraining;
    private int breeding;
    private int explorerTraining;
    private int buildingRate;

    public int getFoodProduction() {
        return foodProduction;
    }

    public void incrementFoodProduction() {
        this.foodProduction += foodProductionIncrement;
    }

    public int getOreProduction() {
        return oreProduction;
    }

    public void incrementOreProduction() {
        this.oreProduction += foodProductionIncrement;
    }

    public int getEnergyProduction() {
        return energyProduction;
    }

    public void incrementEnergyProduction() {
        this.energyProduction += energyProductionIncrement;
    }

    public int getTechnologyProduction() {
        return technologyProduction;
    }

    public void incrementTechnologyProduction() {
        this.technologyProduction += technologyProductionIncrement;
    }

    public int getSoldierTraining() {
        return soldierTraining;
    }

    public void incrementSoldierTraining() {
        this.soldierTraining += soldierTrainingIncrement;
    }

    public int getBreeding() {
        return breeding;
    }

    public void incrementBreeding() {
        this.breeding += breedingIncrement;
    }

    public int getExplorerTraining() {
        return explorerTraining;
    }

    public void incrementExplorerTraining() {
        this.explorerTraining += explorerTrainingIncrement;
    }

    public int getBuildingRate() {
        return buildingRate;
    }

    public void incrementBuildingRate() {
        this.buildingRate += buildingRateIncrement;
    }

    public int getFoodProductionIncrement() {
        return foodProductionIncrement;
    }

    public int getOreProductionIncrement() {
        return oreProductionIncrement;
    }

    public int getEnergyProductionIncrement() {
        return energyProductionIncrement;
    }

    public int getTechnologyProductionIncrement() {
        return technologyProductionIncrement;
    }

    public int getSoldierTrainingIncrement() {
        return soldierTrainingIncrement;
    }

    public int getBreedingIncrement() {
        return breedingIncrement;
    }

    public int getExplorerTrainingIncrement() {
        return explorerTrainingIncrement;
    }

    public int getBuildingRateIncrement() {
        return buildingRateIncrement;
    }

    public int getMaxFoodProductionLevel() {
        return maxFoodProductionLevel;
    }

    public int getMaxOreProductionLevel() {
        return maxOreProductionLevel;
    }

    public int getMaxEnergyProductionLevel() {
        return maxEnergyProductionLevel;
    }

    public int getMaxTechnologyProductionLevel() {
        return maxTechnologyProductionLevel;
    }

    public int getMaxSoldierTrainingLevel() {
        return maxSoldierTrainingLevel;
    }

    public int getMaxBreedingLevel() {
        return maxBreedingLevel;
    }

    public int getMaxExplorerTrainingLevel() {
        return maxExplorerTrainingLevel;
    }

    public int getMaxBuildingRateLevel() {
        return maxBuildingRateLevel;
    }
}
