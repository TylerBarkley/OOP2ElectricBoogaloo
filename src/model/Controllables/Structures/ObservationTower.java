package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class ObservationTower extends Structure {
    //TODO nothing, cause this class is useless
    // HK: ^^^LOLOL

    private ObservationTowerManager observationTowerManager;

    public ObservationTower(){
        observationTowerManager = new ObservationTowerManager();
    }

    public void unassign(){
        observationTowerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        observationTowerManager.setNumOfWorkers_Building(0);
    }

    public void setStats(WorkerStats workerStats){
        observationTowerManager.setWorkerStats(workerStats);
    }

}
