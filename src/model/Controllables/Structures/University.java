package model.Controllables.Structures;



/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class University extends Structure {

    public void harvestScience(){
        //TODO change to assignment number
        getWorkerManager().produceTechnology(getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

}
