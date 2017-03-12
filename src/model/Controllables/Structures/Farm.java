package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends Structure implements Farming{

    private FarmManager farmManager;

    public Farm(){
        farmManager = new FarmManager();
    }

    @Override
    public void doWork(){
        if(farmManager.getNumOfWorkers_Harvesting() > 0){
            harvestFood();
        }
    }

    public void assignWorkersToFarm(Location loc, int numOfWorkers_AssignToFarm){
        farmManager.assignWorkers(loc, numOfWorkers_AssignToFarm, getLocation());
    }

    @Override
    public void unassign(){
        farmManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        farmManager.setNumOfWorkers_Harvesting(0);
        farmManager.setNumOfWorkers_Building(0);
        farmManager.resetWork(getLocation());
    }
    public void harvestFood(){
        int foodMined = farmManager.produceFood(getMyStats().getProductionRate());
        PlayerManager.getInstance().addNutrients(getPid(), foodMined);
    }

    public void setStats(WorkerStats workerStats){
        farmManager.setWorkerStats(workerStats);
    }

    public void setFarmManager(FarmManager farmManager) {
        this.farmManager = farmManager;
    }
}
