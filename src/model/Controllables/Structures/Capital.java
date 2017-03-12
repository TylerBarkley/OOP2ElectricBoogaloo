package model.Controllables.Structures;

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
        if(capitalManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToFarm){
            numOfWorkers_AssignToFarm = capitalManager.getNumOfWorkers_Unassigned();
            capitalManager.assignWorkers(numOfWorkers_AssignToFarm);
            capitalManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
        }
        else{
            capitalManager.setNumOfWorkers_HarvestingFood(numOfWorkers_AssignToFarm);
            capitalManager.assignWorkers(numOfWorkers_AssignToFarm);
        }
        capitalManager.setHarvestingFoodLocation(loc);
    }

    public void assignWorkersToMine(Location loc, int numOfWorkers_AssignToMine){
        if(capitalManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToMine){
            numOfWorkers_AssignToMine = capitalManager.getNumOfWorkers_Unassigned();
            capitalManager.assignWorkers(numOfWorkers_AssignToMine);
            capitalManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
        }
        else{
            capitalManager.setNumOfWorkers_HarvestingOre(numOfWorkers_AssignToMine);
            capitalManager.assignWorkers(numOfWorkers_AssignToMine);
        }
        capitalManager.setHarvestingOreLocation(loc);
    }

    @Override
    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant) {
        if(capitalManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToPowerPlant){
            numOfWorkers_AssignToPowerPlant = capitalManager.getNumOfWorkers_Unassigned();
            capitalManager.assignWorkers(numOfWorkers_AssignToPowerPlant);
            capitalManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
        }
        else{
            capitalManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
            capitalManager.assignWorkers(numOfWorkers_AssignToPowerPlant);
        }
        capitalManager.setHarvestingEnergyLocation(loc);
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

    public void setCapitalManager(CapitalManager capitalManager) {
        this.capitalManager = capitalManager;
    }
}
