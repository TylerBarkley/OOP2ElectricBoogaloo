package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class PowerPlant extends Structure {

    public void harvestEnergy(){
        //TODO change to assignment number
        getWorkerManager().produceEnergy(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

}
