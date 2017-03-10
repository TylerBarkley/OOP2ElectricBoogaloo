package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Mine extends Structure {

    public void harvestOre(){
        //TODO change to assignment number
        getWorkerManager().produceOre(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

}
