package model.Controllables.Structures;

import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Mine extends Structure implements Mining{

    private WorkerManager workerManager;
    
    @Override
    public void doWork(){
        if(workerManager.getNumOfWorkers_HarvestingOre() > 0){
            harvestOre();
        }
    }
    
    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToMine){
            numOfWorkers_AssignToMine = workerManager.getNumOfWorkers_Unassigned();
            workerManager.setNumOfWorkers_Unassigned(0);
            workerManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
            workerManager.assignWorkers(numOfWorkers_AssignToMine);
        }
        workerManager.setHarvestingOreLocation(loc);
    }
    
    @Override
    public void unassign(){
        workerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        workerManager.setNumOfWorkers_HarvestingOre(0);
    }
    
    public void harvestOre(){
        int oreMined = workerManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPid(), oreMined);
    }

    @Override
    public void setWorkerManager(WorkerManager workerManager) {
        this.workerManager = workerManager;
    }
}
