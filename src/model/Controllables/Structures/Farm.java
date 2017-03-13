package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends Structure implements Farming{

    private FarmManager farmManager;
    private int builtPercentage;

    public Farm(){
        builtPercentage = 0;
        farmManager = new FarmManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == true) {
            harvestFood();
        }
        else{
            build();
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

    @Override
    public void build() {
        builtPercentage += farmManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
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

    public void addWorker(int number) {
        farmManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        farmManager.removeUnassigned(number);
    }

    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }
}
