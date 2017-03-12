package model.Controllables.Structures;

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
