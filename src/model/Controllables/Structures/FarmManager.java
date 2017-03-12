package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;
import java.lang.Math;

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
    private ArrayList<Location> adjacencies;

    public FarmManager(){
        workerStats = new WorkerStats();
    }

    public int produceFood(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getFoodProduction()) * numOfWorkers_HarvestingFood;
        return resourceManager.mineFood(harvestingFoodLocation, amount);
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
        if(loc != harvestingFoodLocation && resourceManager.isWorking(loc) == true){
            return;
        }
        if((numOfWorkers_HarvestingFood + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned = numOfWorkers_HarvestingFood + numOfWorkers_Unassigned;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_HarvestingFood = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_HarvestingFood;
            resourceManager.setWorking(harvestingFoodLocation, false);
            harvestingFoodLocation = loc;
            resourceManager.setWorking(harvestingFoodLocation, true);
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = assignNum;
            numOfWorkers_HarvestingFood = 0;
            resourceManager.setWorking(harvestingFoodLocation, false);
            harvestingFoodLocation = myLoc;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_HarvestingFood + numOfWorkers_Unassigned;
            numOfWorkers_HarvestingFood = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_HarvestingFood;
            resourceManager.setWorking(harvestingFoodLocation, false);
            harvestingFoodLocation = loc;
            resourceManager.setWorking(harvestingFoodLocation, true);
        }
    }

    public void resetWork(Location myLoc){
        if(harvestingFoodLocation == null){
            return;
        }
        resourceManager.setWorking(harvestingFoodLocation, false);
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
