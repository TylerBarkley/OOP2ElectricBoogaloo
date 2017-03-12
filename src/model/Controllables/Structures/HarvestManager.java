package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public abstract class HarvestManager extends WorkerManager{

    private ResourceManager resourceManager;
    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_Harvesting;
    private int numOfWorkers_Building;

    private Location harvestingLocation;
    private ArrayList<Location> adjacencies;

    public HarvestManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
    }

    public int building() {
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public void assignWorkers(Location loc, int assignNum, Location myLoc){
        adjacencies = myLoc.getAllLocationsWithinRadius(workerStats.getWorkerRadius());
        boolean flag = false;
        for(int i = 0; i < adjacencies.size(); i++){
            if(adjacencies.get(i).equals(loc)){
                flag = true;
            }
        }
        if(flag == false){
            return;
        }
        if(loc != harvestingLocation && resourceManager.isWorking(loc) == true){
            return;
        }
        if((numOfWorkers_Harvesting + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned = numOfWorkers_Harvesting + numOfWorkers_Unassigned;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_Harvesting = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_Harvesting;
            resourceManager.setWorking(harvestingLocation, false);
            harvestingLocation = loc;
            resourceManager.setWorking(harvestingLocation, true);
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = assignNum;
            numOfWorkers_Harvesting = 0;
            resourceManager.setWorking(harvestingLocation, false);
            harvestingLocation = myLoc;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_Harvesting + numOfWorkers_Unassigned;
            numOfWorkers_Harvesting = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_Harvesting;
            resourceManager.setWorking(harvestingLocation, false);
            harvestingLocation = loc;
            resourceManager.setWorking(harvestingLocation, true);
        }
    }

    public void resetWork(Location myLoc){
        if(harvestingLocation == null){
            return;
        }
        resourceManager.setWorking(harvestingLocation, false);
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

    public int getNumOfWorkers_Harvesting() {
        return numOfWorkers_Harvesting;
    }

    public void setNumOfWorkers_Harvesting(int numOfWorkers_Harvesting) {
        this.numOfWorkers_Harvesting = numOfWorkers_Harvesting;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public Location getHarvestingLocation(){
        return this.harvestingLocation;
    }

    public void setHarvestingLocation(Location harvestingLocation) {
        this.harvestingLocation = harvestingLocation;
    }
}
