package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;
import java.lang.Math;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class FarmManager extends HarvestManager{

    public FarmManager(){    }

    public int produceFood(int structureProductionRate){
        int amount = (structureProductionRate + getWorkerStats().getFoodProduction()) * getNumOfWorkers_Harvesting();
        return getResourceManager().mineFood(getHarvestingLocation(), amount);
    }

}
