package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class MineManager extends WorkerManager{

    private WorkerStats workerStats;
    private ResourceManager resourceManager;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingOre;
    private int numOfWorkers_Building;

    private Location harvestingOreLocation;

    public MineManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
    }

    public int produceOre(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getOreProduction()) * numOfWorkers_HarvestingOre;
        return resourceManager.mineOre(harvestingOreLocation, amount);
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

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_HarvestingOre() {
        return numOfWorkers_HarvestingOre;
    }

    public void setNumOfWorkers_HarvestingOre(int numOfWorkers_HarvestingOre) {
        this.numOfWorkers_HarvestingOre = numOfWorkers_HarvestingOre;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public void setHarvestingOreLocation(Location harvestingOreLocation) {
        this.harvestingOreLocation = harvestingOreLocation;
    }
}
