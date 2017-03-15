package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends HarvestStructure implements Farming{

    private FarmManager farmManager;
    private int builtPercentage;

    public Farm(){
        builtPercentage = 0;
        farmManager = new FarmManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == false) {
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
        farmManager.unassignAll();
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
        PlayerManager.getInstance().addNutrients(getPlayerID(), foodMined);
    }

    public void setStats(WorkerStats workerStats){
        farmManager.setWorkerStats(workerStats);
    }

    public void setFarmManager(FarmManager farmManager) {
        this.farmManager = farmManager;
    }

    public void addWorker(int number) {
        addNewWorkers(number);
        farmManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        removeOldWorkers(number);
        farmManager.removeUnassigned(number);
    }

    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }
}
