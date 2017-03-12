package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class ObservationTowerManager extends WorkerManager{

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_Building;

    private WorkerStats workerStats;

    public ObservationTowerManager(){
        workerStats = new WorkerStats();
    }

    public int building() {
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }
}
