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

    public int produceOre(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getOreProduction()) * numOfWorkers;
        return RM.mineOre(loc, amount);
    }

    public int produceTechnology(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getTechnologyProduction()) * numOfWorkers;
        return amount;
    }

    public int produceEnergy(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getEnergyProduction()) * numOfWorkers;
        return RM.mineEnegry(loc, amount);
    }

    public int produceFood(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getFoodProduction()) * numOfWorkers;
        return RM.mineFood(loc, amount);
    }

    public int breeding(Location loc, int numOfWorkers){
        int newWorkers = ((WS.getBreeding() * numOfWorkers)/2);
        return newWorkers;
    }

    public int building(Location loc, int numOfWorker){
        int percentageBuilt = WS.getBuildingRate() * 2 * numOfWorker;
        return percentageBuilt;
    }

    public ResourceManager getRM() {
        return RM;
    }

    public WorkerStats getWS() {
        return WS;
    }

    public void setWS(WorkerStats WS) {
        this.WS = WS;
    }
}
