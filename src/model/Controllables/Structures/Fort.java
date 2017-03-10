package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Fort extends Structure {

    private int numOfSoldiers;

    public void makeSoldiers(){
        //TODO change to assignment number
        getWorkerManager().trainSoldier(getNumTotalOfWorkers(), numOfSoldiers);
    }

    public void setNumOfSoldiers(int numOfSoldiers) {
        this.numOfSoldiers = numOfSoldiers;
    }
}
