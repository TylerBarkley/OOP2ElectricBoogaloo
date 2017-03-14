package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Mine extends Structure implements Mining{

    private MineManager mineManager;
    private int builtPercentage;

    public Mine(){
        builtPercentage = 0;
        mineManager = new MineManager();
        setBeingBuilt(true);
    }
    
    @Override
    public void doWork(){
        if(getBeingBuilt() == false) {
            harvestOre();
        }
        else{
            build();
        }
    }
    
    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        mineManager.assignWorkers(loc, numOfWorkers_AssignToMine, getLocation());
    }
    
    @Override
    public void unassign(){
        mineManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        mineManager.setNumOfWorkers_Harvesting(0);
        mineManager.setNumOfWorkers_Building(0);
        mineManager.resetWork(getLocation());
    }

    @Override
    public void build() {
        builtPercentage += mineManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
    }

    public void harvestOre(){
        int oreMined = mineManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPlayerID(), oreMined);
    }

    public void setStats(WorkerStats workerStats){
        mineManager.setWorkerStats(workerStats);
    }

    public void setMineManager(MineManager mineManager) {
        this.mineManager = mineManager;
    }

    public void addWorker(int number) {
        mineManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        mineManager.removeUnassigned(number);
    }

    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }
}
