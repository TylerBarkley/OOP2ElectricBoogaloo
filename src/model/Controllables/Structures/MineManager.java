package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class MineManager extends HarvestManager{

    public MineManager(){

    }

    public int produceOre(int structureProductionRate){
        int amount = (structureProductionRate + getWorkerStats().getOreProduction()) * getNumOfWorkers_Harvesting();
        return getResourceManager().mineOre(getHarvestingLocation(), amount);
    }

}
