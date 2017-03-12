package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Capital extends Structure implements  Farming, Mining, Energizing{

    private CapitalManager capitalManager;

    public Capital(){
        capitalManager = new CapitalManager();
    }

    @Override
    public void doWork() {
        if(capitalManager.getNumOfWorkers_HarvestingOre() > 0){
            harvestOre();
        }
        if(capitalManager.getNumOfWorkers_HarvestingFood() > 0){
            harvestFood();
        }
        if(capitalManager.getNumOfWorkers_HarvestingEnergy() > 0){
            harvestEnergy();
        }
        if(capitalManager.getNumOfWorkers_Breeding() > 0){
            breedWorkers();
        }
        if(capitalManager.getNumOfWorkers_ExplorerTraining() > 0){
            makeExplorer();
        }
        if(capitalManager.getNumOfWorkers_Building() > 0){
            //TODO add build function
        }
    }

    public void harvestOre(){
        int oreMined = capitalManager.produceOre(getMyStats().getProductionRate());
        PlayerManager.getInstance().addMetal(getPid(), oreMined);
    }

    public void harvestFood(){
        int foodMined = capitalManager.produceFood(getMyStats().getProductionRate());
        PlayerManager.getInstance().addNutrients(getPid(), foodMined);
    }

    public void harvestEnergy(){
        int energyMined = capitalManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPid(), energyMined);
    }

    public void breedWorkers(){
        //TODO add to PM
        capitalManager.breeding();
    }

    public void makeExplorer(){
        //TODO add to PM
        capitalManager.trainExplorer();
    }

    public void assignWorkersToFarm(Location loc, int numOfWorkers_AssignToFarm){
        capitalManager.assignWorkersFood(loc, numOfWorkers_AssignToFarm, getLocation());
    }

    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        capitalManager.assignWorkersMine(loc, numOfWorkers_AssignToMine, getLocation());
    }

    @Override
    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant) {
        capitalManager.assignWorkersEnergy(loc, numOfWorkers_AssignToPowerPlant, getLocation());
    }

    public void assignWorkersToBreed(int numOfWorkers_Breeding){
        capitalManager.assignWorkersBreeding(numOfWorkers_Breeding);
    }

    public void assignWorkersToTrainExplorers(int numOfWorkers_TrainingExplorers){
        capitalManager.assignWorkersTrainingExplorers(numOfWorkers_TrainingExplorers);
    }

    @Override
    public void unassign(){
        capitalManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        capitalManager.setNumOfWorkers_HarvestingFood(0);
        capitalManager.setNumOfWorkers_HarvestingOre(0);
        capitalManager.setNumOfWorkers_HarvestingEnergy(0);
        capitalManager.setNumOfWorkers_ExplorerTraining(0);
        capitalManager.setNumOfWorkers_Breeding(0);
        capitalManager.setNumOfWorkers_Building(0);
    }

    public void setStats(WorkerStats workerStats){
        capitalManager.setWorkerStats(workerStats);
    }

    public void setCapitalManager(CapitalManager capitalManager) {
        this.capitalManager = capitalManager;
    }
}
