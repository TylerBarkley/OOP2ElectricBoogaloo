package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/8/2017.
 */
public class WorkerManager {
    private ResourceManager rm;
    private WorkerStats ws;

    public WorkerManager(){
        ws = new WorkerStats();
        rm = new ResourceManager();

    }

    public int produceOre(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + ws.getOreProduction()) * numOfWorkers;
        return rm.mineOre(loc, amount);
    }

    public int produceTechnology(int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + ws.getTechnologyProduction()) * numOfWorkers;
        return amount;
    }

    public int produceEnergy(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + ws.getEnergyProduction()) * numOfWorkers;
        return rm.mineEnergy(loc, amount);
    }

    public int produceFood(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + ws.getFoodProduction()) * numOfWorkers;
        return rm.mineFood(loc, amount);
    }

    public int breeding(int numOfWorkers){
        int newWorkers = ((ws.getBreeding() * numOfWorkers)/2);
        return newWorkers;
    }

    public int building(int numOfWorker){
        int percentageBuilt = ws.getBuildingRate() * 2 * numOfWorker;
        return percentageBuilt;
    }

    public int trainSoldier(int numOfWorker, int numOfSoldiers){
        if(numOfWorker < 1){
            return 0;
        }
        int percentageTrained = ws.getSoldierTraining() * numOfWorker + (2 * numOfSoldiers * ws.getSoldierTraining());
        return percentageTrained;
    }

    public int trainExplorer(int numOfWorker){
        int percentageTrained = ws.getExplorerTraining() * numOfWorker * 10;
        return percentageTrained;
    }

    public ResourceManager getRM() {
        return rm;
    }

    public void setRM(ResourceManager rm){
        this.rm = rm;
    }

    public WorkerStats getWS() {
        return ws;
    }

    public void setWS(WorkerStats WS) {
        this.ws = WS;
    }
}
