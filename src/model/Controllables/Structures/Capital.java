package model.Controllables.Structures;

import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Capital extends Structure implements  Farming, Mining, Energizing{

    private WorkerManager workerManager;
    private int numOfWorkers;

    @Override
    public void doWork() {
        if(workerManager.getNumOfWorkers_HarvestingOre() > 0){
            harvestOre();
        }
        if(workerManager.getNumOfWorkers_HarvestingFood() > 0){
            harvestFood();
        }
        if(workerManager.getNumOfWorkers_HarvestingEnergy() > 0){
            harvestEnergy();
        }
        if(workerManager.getNumOfWorkers_Breeding() > 0){
            breedWorkers();
        }
        if(workerManager.getNumOfWorkers_ExplorerTraining() > 0){
            makeExplorer();
        }
        if(workerManager.getNumOfWorkers_Building() > 0){
            //TODO add build function
        }
    }

    public void harvestOre(){
        int oreMined = workerManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPid(), oreMined);
    }

    public void harvestFood(){
        int foodMined = workerManager.produceFood(getMyStats().getProductionRate());
        PlayerManager.getInstance().addNutrients(getPid(), foodMined);
    }

    public void harvestEnergy(){
        int energyMined = workerManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPid(), energyMined);
    }

    public void breedWorkers(){
        //TODO add to PM
        workerManager.breeding();
    }

    public void makeExplorer(){
        //TODO add to PM
        workerManager.trainExplorer();
    }

    public void assignWorkersToFarm(Location loc, int numOfWorkers_AssignToFarm){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToFarm){
            numOfWorkers_AssignToFarm = workerManager.getNumOfWorkers_Unassigned();
            workerManager.assignWorkers(numOfWorkers_AssignToFarm);
            workerManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
            workerManager.assignWorkers(numOfWorkers_AssignToFarm);
        }
        workerManager.setHarvestingFoodLocation(loc);
    }

    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToMine){
            numOfWorkers_AssignToMine = workerManager.getNumOfWorkers_Unassigned();
            workerManager.assignWorkers(numOfWorkers_AssignToMine);
            workerManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
        }
        else{
            workerManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
            workerManager.assignWorkers(numOfWorkers_AssignToMine);
        }
        workerManager.setHarvestingOreLocation(loc);
    }

    @Override
    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant) {
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToPowerPlant){
            numOfWorkers_AssignToPowerPlant = workerManager.getNumOfWorkers_Unassigned();
            workerManager.assignWorkers(numOfWorkers_AssignToPowerPlant);
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
        workerManager.setNumOfWorkers_Unassigned(getNumOfWorkers());
        workerManager.setNumOfWorkers_HarvestingFood(0);
        workerManager.setNumOfWorkers_HarvestingOre(0);
        workerManager.setNumOfWorkers_HarvestingEnergy(0);
        workerManager.setNumOfWorkers_ExplorerTraining(0);
        workerManager.setNumOfWorkers_Breeding(0);
    }

    public int getNumOfWorkers() {
        return numOfWorkers;
    }

    public void setNumOfWorkers(int numOfWorkers) {
        this.numOfWorkers = numOfWorkers;
    }

    @Override
    public void setWorkerManager(WorkerManager workerManager) {
        this.workerManager = workerManager;
    }
}
