package model.Controllables.Structures;

import model.Location;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public abstract class HarvestManager extends WorkerManager{
/*
    private int

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
            numOfWorkers_Unassigned = assignNum;
            numOfWorkers_HarvestingOre = 0;
            resourceManager.setWorking(harvestingOreLocation, false);
            harvestingOreLocation = myLoc;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_HarvestingOre + numOfWorkers_Unassigned;
            numOfWorkers_HarvestingOre = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - numOfWorkers_HarvestingOre;
            resourceManager.setWorking(harvestingOreLocation, false);
            harvestingOreLocation = loc;
            resourceManager.setWorking(harvestingOreLocation, true);
        }
    }*/
}
