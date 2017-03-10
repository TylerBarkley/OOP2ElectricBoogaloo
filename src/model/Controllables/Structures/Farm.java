package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Farm extends Structure {

    public void harvestFood(){
        //TODO change to assignment number
        getWorkerManager().produceFood(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

}
