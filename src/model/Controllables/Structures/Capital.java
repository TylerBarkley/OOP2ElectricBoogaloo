package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.ProductionManager;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Capital extends Structure implements  Farming, Mining, Energizing{

    private CapitalManager capitalManager;
    private int exBuildPercentage;

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
        PlayerManager.getInstance().addMetal(getPlayerID(), oreMined);
    }

    public void harvestFood(){
        int foodMined = capitalManager.produceFood(getMyStats().getProductionRate());
        PlayerManager.getInstance().addNutrients(getPlayerID(), foodMined);
    }

    public void harvestEnergy(){
        int energyMined = capitalManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPlayerID(), energyMined);
    }

    public void breedWorkers(){
        int amountAdded = capitalManager.breeding();
        boolean added = false;
        added = PlayerManager.getInstance().addWorker(getPlayerID(), amountAdded);
        if(added == true){
            addWorker(amountAdded);
        }
    }

    public void makeExplorer(){
        exBuildPercentage += capitalManager.trainExplorer();
        if(exBuildPercentage > 99){
            ProductionManager.getInstance().produceExplorer(this);
            exBuildPercentage -= 100;
        }
    }

    public void assignWorkersToFarm(Location loc, int numOfWorkers_AssignToFarm){
        capitalManager.assignWorkersFood(loc, numOfWorkers_AssignToFarm, getLocation());
    }

    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        capitalManager.assignWorkersMine(loc, numOfWorkers_AssignToMine, getLocation());
    }

    @Override
    public void assignWorkersToPowerHarvest(Location loc, int numOfWorkers_AssignToPowerPlant) {
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
        capitalManager.unassignAll();
        capitalManager.resetWork(getLocation());
    }

    @Override
    public void build() {    }

    @Override
    public void addWorker(int number) {
        addNewWorkers(number);
        capitalManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        removeOldWorkers(number);
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

    public void setExBuildPercentage(int exBuildPercentage) {
        this.exBuildPercentage = exBuildPercentage;
    }

    public int getExBuildPercentage(){
        return this.exBuildPercentage;
    }
    public String toString(){return "Capital";}
}
