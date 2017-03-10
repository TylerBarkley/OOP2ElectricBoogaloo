package model.Controllables.Structures;


import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class University extends Structure {

    private WorkerManager workerManager;

    @Override
    public void doWork(){
        if(workerManager.getNumOfWorkers_HarvestingTechnology() > 0){
            harvestScience();
        }
    }

    public void harvestScience(){
        workerManager.produceTechnology(getMyStats().getProductionRate());
        //TODO wat
    }

    public void assignWorkersToHarvestTechnology(int numOfWorkers_HarvestingTech){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_HarvestingTech){
            numOfWorkers_HarvestingTech = workerManager.getNumOfWorkers_Unassigned();
            workerManager.setNumOfWorkers_Unassigned(0);
            workerManager.setNumOfWorkers_HarvestingTechnology(numOfWorkers_HarvestingTech);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingTechnology(numOfWorkers_HarvestingTech);
            workerManager.assignWorkers(numOfWorkers_HarvestingTech);
        }
    }

    @Override
    public void unassign(){
        workerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        workerManager.setNumOfWorkers_HarvestingEnergy(0);
    }

}
