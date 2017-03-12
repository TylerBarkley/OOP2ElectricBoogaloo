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

    @Override
    public void doWork() {
        if(observationTowerManager.getNumOfWorkers_Building() > 0){
            observationTowerManager.building();
        }
    }

    public void unassign(){
        observationTowerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        observationTowerManager.setNumOfWorkers_Building(0);
    }

    public void setStats(WorkerStats workerStats){
        observationTowerManager.setWorkerStats(workerStats);
    }

    public void setObservationTowerManager(ObservationTowerManager observationTowerManager){
        this.observationTowerManager = observationTowerManager;
    }

    public void addWorker(int number) {
        observationTowerManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        observationTowerManager.removeUnassigned(number);
    }

}
