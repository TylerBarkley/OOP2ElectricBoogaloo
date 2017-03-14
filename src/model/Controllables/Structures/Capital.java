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
        setBeingBuilt(false);
        capitalManager = new CapitalManager();
    }

    @Override
    public void doWork() {
        harvestOre();
        harvestFood();
        harvestEnergy();
        breedWorkers();
        makeExplorer();
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
        int amountAdded = capitalManager.breeding();
        boolean added = false;
        added = PlayerManager.getInstance().addWorker(getPid(), amountAdded);
        if(added == true){
            addWorker(amountAdded);
        }
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
        capitalManager.resetWork(getLocation());
    }

    @Override
    public void build() {    }

    @Override
    public void addWorker(int number) {
        capitalManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        capitalManager.removeUnassigned(number);
    }

    public void setStats(WorkerStats workerStats){
        capitalManager.setWorkerStats(workerStats);
    }

    public void setCapitalManager(CapitalManager capitalManager) {
        this.capitalManager = capitalManager;
    }

    public CapitalManager getCapitalManager(){
        return this.capitalManager;
    }
}
