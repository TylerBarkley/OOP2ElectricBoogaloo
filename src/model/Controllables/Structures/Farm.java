package model.Controllables.Structures;

import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends Structure implements Farming{

    private WorkerManager workerManager;

    @Override
    public void doWork(){
        if(workerManager.getNumOfWorkers_HarvestingFood() > 0){
            harvestFood();
        }
    }

    public void assignWorkersToFarm(Location loc, int numOfWorkers_AssignToFarm){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToFarm){
            numOfWorkers_AssignToFarm = workerManager.getNumOfWorkers_Unassigned();
            workerManager.setNumOfWorkers_Unassigned(0);
            workerManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
            workerManager.assignWorkers(numOfWorkers_AssignToFarm);
        }
        workerManager.setHarvestingFoodLocation(loc);
    }

    @Override
    public void unassign(){
        workerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        workerManager.setNumOfWorkers_HarvestingFood(0);
    }

    public void harvestFood(){
        int foodMined = workerManager.produceFood(getMyStats().getProductionRate());
        PlayerManager.getInstance().addNutrients(getPid(), foodMined);
    }

    @Override
    public void setWorkerManager(WorkerManager workerManager) {
        this.workerManager = workerManager;
    }
}
