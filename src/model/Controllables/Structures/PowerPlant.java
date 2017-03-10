package model.Controllables.Structures;

import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class PowerPlant extends Structure implements Energizing {

    private WorkerManager workerManager;

    @Override
    public void doWork(){
        if(workerManager.getNumOfWorkers_HarvestingEnergy() > 0){
            harvestEnergy();
        }
    }

    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToPowerPlant){
            numOfWorkers_AssignToPowerPlant = workerManager.getNumOfWorkers_Unassigned();
            workerManager.setNumOfWorkers_Unassigned(0);
            workerManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
            workerManager.assignWorkers(numOfWorkers_AssignToPowerPlant);
        }
        workerManager.setHarvestingEnergyLocation(loc);
    }

    @Override
    public void unassign(){
        workerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        workerManager.setNumOfWorkers_HarvestingEnergy(0);
    }

    public void harvestEnergy(){
        int energyMined = workerManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPid(), energyMined);
    }

    @Override
    public void setWorkerManager(WorkerManager workerManager) {
        this.workerManager = workerManager;
    }
}
