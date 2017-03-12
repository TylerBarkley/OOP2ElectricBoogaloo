package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class PowerPlantManager extends HarvestManager{


    public PowerPlantManager(){    }

    public int produceEnergy(int structureProductionRate){
        int amount = (structureProductionRate + getWorkerStats().getEnergyProduction()) * getNumOfWorkers_Harvesting();
        return getResourceManager().mineEnergy(getHarvestingLocation(), amount);
    }

}
