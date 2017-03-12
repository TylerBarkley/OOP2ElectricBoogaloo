package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class FarmManager extends WorkerManager{

    private WorkerStats workerStats;
    private ResourceManager resourceManager;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingFood;
    private int numOfWorkers_Building;

    private Location harvestingFoodLocation;

    public FarmManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
    }

    public int produceFood(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getFoodProduction()) * numOfWorkers_HarvestingFood;
        return resourceManager.mineFood(harvestingFoodLocation, amount);
    }

    public int building() {
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public void assignWorkers(int assignNum){
        numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
    }

    public WorkerStats getWorkerStats() {
        return workerStats;
    }

    public void setWorkerStats(WorkerStats workerStats) {
        this.workerStats = workerStats;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_HarvestingFood() {
        return numOfWorkers_HarvestingFood;
    }

    public void setNumOfWorkers_HarvestingFood(int numOfWorkers_HarvestingFood) {
        this.numOfWorkers_HarvestingFood = numOfWorkers_HarvestingFood;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setHarvestingFoodLocation(Location harvestingFoodLocation) {
        this.harvestingFoodLocation = harvestingFoodLocation;
    }
}
