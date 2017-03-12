package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class PowerPlantManager extends WorkerManager{

    private WorkerStats workerStats;
    private ResourceManager resourceManager;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingEnergy;
    private int numOfWorkers_Building;

    private Location harvestingEnergyLocation;

    public PowerPlantManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
    }

    public int produceEnergy(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getEnergyProduction()) * numOfWorkers_HarvestingEnergy;
        return resourceManager.mineEnergy(harvestingEnergyLocation, amount);
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

    public int getNumOfWorkers_HarvestingEnergy() {
        return numOfWorkers_HarvestingEnergy;
    }

    public void setNumOfWorkers_HarvestingEnergy(int numOfWorkers_HarvestingEnergy) {
        this.numOfWorkers_HarvestingEnergy = numOfWorkers_HarvestingEnergy;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public void setHarvestingEnergyLocation(Location harvestingEnergyLocation) {
        this.harvestingEnergyLocation = harvestingEnergyLocation;
    }
}
