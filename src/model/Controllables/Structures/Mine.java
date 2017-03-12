package model.Controllables.Structures;

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
        if(mineManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToMine){
            numOfWorkers_AssignToMine = mineManager.getNumOfWorkers_Unassigned();
            mineManager.setNumOfWorkers_Unassigned(0);
            mineManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
        }
        else{
            mineManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
            mineManager.assignWorkers(numOfWorkers_AssignToMine);
        }
        mineManager.setHarvestingOreLocation(loc);
    }
    
    @Override
    public void unassign(){
        mineManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        mineManager.setNumOfWorkers_HarvestingOre(0);
        mineManager.setNumOfWorkers_Building(0);
    }
    
    public void harvestOre(){
        int oreMined = mineManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPid(), oreMined);
    }

    public void setMineManager(MineManager mineManager) {
        this.mineManager = mineManager;
    }
}
