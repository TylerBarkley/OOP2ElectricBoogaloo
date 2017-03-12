package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;

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
    private ArrayList<Location> adjacencies;

    public MineManager(){
        workerStats = new WorkerStats();
    }

    public int produceOre(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getOreProduction()) * numOfWorkers_HarvestingOre;
        return resourceManager.mineOre(harvestingOreLocation, amount);
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
        if(loc != harvestingOreLocation && resourceManager.isWorking(loc) == true){
            return;
        }
        if((numOfWorkers_HarvestingOre + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned = numOfWorkers_HarvestingOre + numOfWorkers_Unassigned;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_HarvestingOre = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_HarvestingOre;
            resourceManager.setWorking(harvestingOreLocation, false);
            harvestingOreLocation = loc;
            resourceManager.setWorking(harvestingOreLocation, true);
        }
        else if(assignNum < 1){
            numOfWorkers_HarvestingOre = 0;
            numOfWorkers_Unassigned = assignNum;
            resourceManager.setWorking(harvestingOreLocation, false);
            harvestingOreLocation = myLoc;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_HarvestingOre + numOfWorkers_Unassigned;
            numOfWorkers_HarvestingOre = Math.min(workerStats.getWorkerDensity(), assignNum);
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_HarvestingOre;
            resourceManager.setWorking(harvestingOreLocation, false);
            harvestingOreLocation = loc;
            resourceManager.setWorking(harvestingOreLocation, true);
        }
    }

    public void resetWork(Location myLoc){
        if(harvestingOreLocation == null){
            return;
        }
        resourceManager.setWorking(harvestingOreLocation, false);
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
