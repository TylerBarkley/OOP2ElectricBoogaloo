package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class MineManager extends HarvestManager{

    public MineManager(){

    }

    public int produceOre(int structureProductionRate){
        int amount = ((structureProductionRate + getWorkerStats().getOreProduction()) * getNumOfWorkers_Harvesting())*8;
        return getResourceManager().mineOre(getHarvestingLocation(), amount);
    }

}
