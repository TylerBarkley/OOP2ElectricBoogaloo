package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class FortManager extends WorkerManager{

    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_Building;
    private int numOfWorkers_SoldierTraining;

    public FortManager(){
        workerStats = new WorkerStats();
    }

    public int building() {
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public int trainSoldier(int numOfSoldiers){
        if(numOfWorkers_SoldierTraining < 1){
            return 0;
        }

        int percentageTrained = workerStats.getSoldierTraining() * numOfWorkers_SoldierTraining + (2 * numOfSoldiers * workerStats.getSoldierTraining());
        return percentageTrained;
    }

    public void assignWorkers(int assignNum){
        numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
    }

    public WorkerStats getWorkerStats() {
        return workerStats;
    }

    public void setWorkerStats(WorkerStats workerStats) {
        this.workerStats = workerStats;
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

    public int getNumOfWorkers_SoldierTraining() {
        return numOfWorkers_SoldierTraining;
    }

    public void setNumOfWorkers_SoldierTraining(int numOfWorkers_SoldierTraining) {
        this.numOfWorkers_SoldierTraining = numOfWorkers_SoldierTraining;
    }
}
