package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/8/2017.
 */
public class WorkerManager {
    private ResourceManager RM;
    private WorkerStats WS;

    public WorkerManager(){
        WS = new WorkerStats();
        RM = new ResourceManager();

    }

    public int produceOre(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getOreProduction()) * numOfWorkers;
        return RM.mineOre(loc, amount);
    }

    public int produceTechnology(int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getTechnologyProduction()) * numOfWorkers;
        return amount;
    }

    public int produceEnergy(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getEnergyProduction()) * numOfWorkers;
        return RM.mineEnergy(loc, amount);
    }

    public int produceFood(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getFoodProduction()) * numOfWorkers;
        return RM.mineFood(loc, amount);
    }

    public int breeding(int numOfWorkers){
        int newWorkers = ((WS.getBreeding() * numOfWorkers)/2);
        return newWorkers;
    }

    public int building(int numOfWorker){
        int percentageBuilt = WS.getBuildingRate() * 2 * numOfWorker;
        return percentageBuilt;
    }

    public int trainSoldier(int numOfWorker, int numOfSoldiers){
        if(numOfWorker < 1){
            return 0;
        }
        int percentageTrained = WS.getSoldierTraining() * numOfWorker + (2 * numOfSoldiers * WS.getSoldierTraining());
        return percentageTrained;
    }

    public int trainExplorer(int numOfWorker){
        int percentageTrained = WS.getExplorerTraining() * numOfWorker * 10;
        return percentageTrained;
    }

    public ResourceManager getRM() {
        return RM;
    }

    public void setRM(ResourceManager rm){
        this.RM = rm;
    }

    public WorkerStats getWS() {
        return WS;
    }

    public void setWS(WorkerStats WS) {
        this.WS = WS;
    }
}
