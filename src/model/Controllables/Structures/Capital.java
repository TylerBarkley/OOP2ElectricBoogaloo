package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Capital extends Structure {

    public void harvestOre(){
        //TODO change to assignment number
        getWorkerManager().produceOre(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

    public void harvestFood(){
        //TODO change to assignment number
        getWorkerManager().produceFood(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

    public void harvestEnergy(){
        //TODO change to assignment number
        getWorkerManager().produceEnergy(getLocation(), getMyStats().getProductionRate(), getNumTotalOfWorkers());
    }

    public void breedWorkers(){
        //TODO change to assignment number
        getWorkerManager().breeding(getNumTotalOfWorkers());
    }

    public void makeExplorer(){
        //TODO change to assignment number
        getWorkerManager().trainExplorer(getNumTotalOfWorkers());
    }

}
