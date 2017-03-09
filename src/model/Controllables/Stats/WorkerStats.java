package model.Controllables.Stats;

/**
 * Created by Tyler Barkley on 3/8/2017.
 */
public class WorkerStats {

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

    public void setFoodProduction(int foodProduction) {
        this.foodProduction = foodProduction;
    }

    public int getOreProduction() {
        return oreProduction;
    }

    public void setOreProduction(int oreProduction) {
        this.oreProduction = oreProduction;
    }

    public int getEnergyProduction() {
        return energyProduction;
    }

    public void setEnergyProduction(int energyProduction) {
        this.energyProduction = energyProduction;
    }

    public int getTechnologyProduction() {
        return technologyProduction;
    }

    public void setTechnologyProduction(int technologyProduction) {
        this.technologyProduction = technologyProduction;
    }

    public int getSoldierTraining() {
        return soldierTraining;
    }

    public void setSoldierTraining(int soldierTraining) {
        this.soldierTraining = soldierTraining;
    }

    public int getBreeding() {
        return breeding;
    }

    public void setBreeding(int breeding) {
        this.breeding = breeding;
    }

    public int getExplorerTraining() {
        return explorerTraining;
    }

    public void setExplorerTraining(int explorerTraining) {
        this.explorerTraining = explorerTraining;
    }

    public int getBuildingRate() {
        return buildingRate;
    }

    public void setBuildingRate(int buildingRate) {
        this.buildingRate = buildingRate;
    }
    public void incrementFoodProduction(int increment) {
        this.foodProduction += increment;
    }

    public void incrementEnergyProduction(int increment) {
        this.energyProduction+=increment;
    }

    public void incrementOreProuction(int increment) {
        this.oreProduction += increment;
    }

    public void incrementTechnologyProduction(int increment) {
        this.technologyProduction += increment;
    }

    public void incrementSoldierTraining(int increment) {
        this.soldierTraining +=increment;
    }

    public void incrementBreeding(int increment) {
        this.breeding+= increment;
    }
    public void incrementExplorerTraining(int increment){
        this.explorerTraining+=increment;
    }
    public void incrementBuildingRate(int increment){
        this.buildingRate+=increment;
    }

}
