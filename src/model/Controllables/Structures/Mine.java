package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Mine extends Structure implements Mining{

    private MineManager mineManager;

    public Mine(){
        mineManager = new MineManager();
    }
    
    @Override
    public void doWork(){
        if(mineManager.getNumOfWorkers_HarvestingOre() > 0){
            harvestOre();
        }
    }
    
    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        mineManager.assignWorkers(loc, numOfWorkers_AssignToMine, getLocation());
    }
    
    @Override
    public void unassign(){
        mineManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        mineManager.setNumOfWorkers_HarvestingOre(0);
        mineManager.setNumOfWorkers_Building(0);
        mineManager.resetWork(getLocation());
    }
    
    public void harvestOre(){
        int oreMined = mineManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPid(), oreMined);
    }

    public void setStats(WorkerStats workerStats){
        mineManager.setWorkerStats(workerStats);
    }

    public void setMineManager(MineManager mineManager) {
        this.mineManager = mineManager;
    }
}
